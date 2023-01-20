package core.singletons;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The utilities class for handling the registering singletons. To use it, call
 * <code>Singletons.registerSingleton(Hello.class, new Hello());</code>
 * This way, it can provide a new layer of abstraction, and if any concrete
 * implementations were to change, any code calling <code>Singletons.get</code>
 * would not need to be changed, unless, or course, you directly call the
 * class itself.
 */
public class Singletons {
    /**
     * The dependencies stored in this dependencies section.
     */
    static private final Map<Class<?>, Object> singletons = new HashMap<>();

    /**
     * The dependency suppliers, used for lazy registration.
     */
    static private final Map<Class<?>, Supplier<?>> lazySingletons =
            new HashMap<>();

    /**
     * Registers a class as a singleton, therefore, it would only be created
     * once.
     * @param cls the class of the object.
     * @param object the object itself.
     * @param <T> the type of the object.
     */
    static public <T> void registerSingleton(Class<T> cls,
                                             T object) {
        if (singletons.containsKey(cls) || lazySingletons.containsKey(cls)) {
            return;
        }
        singletons.put(cls, object);
    }

    /**
     * Registers a class as a lazy singleton. A lazy singleton will only get
     * instantiated if it was used.
     * @param cls the class of the object
     * @param supplier the lazy singleton of the object.
     * @param <T> the type of the object.
     */
    static public <T> void registerLazySingleton(Class<T> cls,
                                                 Supplier<T> supplier) {
        if (singletons.containsKey(cls) || lazySingletons.containsKey(cls)) {
            return;
        }
        lazySingletons.put(cls, supplier);
    }

    /**
     * Gets an instance of the specified class.
     * @param cls the class of the class.
     * @return the object that has been registered for this class.
     * @param <T> the type of the object.
     */
    static public <T> T get(Class<T> cls) {
        if (singletons.containsKey(cls)) {
            return cls.cast(singletons.get(cls));
        } else if (lazySingletons.containsKey(cls)) {
            final T object = cls.cast(lazySingletons.get(cls).get());
            singletons.put(cls, object);
            lazySingletons.remove(cls);
            return object;
        }
        throw new RuntimeException("Dependency for " + cls.getName() + " has " +
                "not been injected.");
    }
}
