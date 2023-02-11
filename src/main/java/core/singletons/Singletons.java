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
    private static final Map<Class<?>, Object> singletons = new HashMap<>();

    /**
     * The dependency suppliers, used for lazy registration.
     */
    private static final Map<Class<?>, Supplier<?>> lazySingletons =
            new HashMap<>();

    /**
     * Registers a class as a singleton, therefore, it would only be created
     * once.
     *
     * @param cls    the class of the object.
     * @param object the object itself.
     * @param <T>    the type of the object.
     */
    public static <T> void registerSingleton(Class<T> cls,
                                             T object) {
        assert (!singletons.containsKey(cls)) || (!lazySingletons
                .containsKey(cls)) : "The class is already registered as a "
                + "singleton";
        singletons.put(cls, object);
    }

    /**
     * Registers a class as a lazy singleton. A lazy singleton will only get
     * instantiated if it was used.
     *
     * @param cls      the class of the object
     * @param supplier the lazy singleton of the object.
     * @param <T>      the type of the object.
     */
    public static <T> void registerLazySingleton(Class<T> cls,
                                                 Supplier<T> supplier) {
        assert (!singletons.containsKey(cls)) || (!lazySingletons
                .containsKey(cls)) : "The class is already registered as a "
                + "singleton";
        lazySingletons.put(cls, supplier);
    }

    /**
     * Re-registers a class as a singleton, therefore, it would only be created
     * once.
     *
     * @param cls the class of the object.
     */
    public static <T> void reRegisterSingleton(Class<T> cls,
                                               T object) {
        singletons.put(cls, object);
    }

    /**
     * Re-registers a class as a lazy singleton. A lazy singleton will only get
     * instantiated if it was used.
     *
     * @param cls the class of the object
     */
    public static <T> void reRegisterLazySingleton(Class<T> cls,
                                                   Supplier<T> supplier) {
        singletons.remove(cls);
        lazySingletons.put(cls, supplier);
    }

    /**
     * Gets an instance of the specified class.
     *
     * @param cls the class of the class.
     * @param <T> the type of the object.
     * @return the object that has been registered for this class.
     */
    public static <T> T get(Class<T> cls) {
        assert (singletons.containsKey(cls) || lazySingletons.containsKey(cls))
                : "The class " + cls.getName() + " has not been registered.";
        if (singletons.containsKey(cls)) {
            return cls.cast(singletons.get(cls));
        } else if (lazySingletons.containsKey(cls)) {
            final T object = cls.cast(lazySingletons.get(cls).get());
            singletons.put(cls, object);
            lazySingletons.remove(cls);
            return object;
        }
        throw new RuntimeException("This should never happen.");
    }
}
