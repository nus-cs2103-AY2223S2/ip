package utilities;

import services.SpeakerRegistry;
import types.IHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class CommandHelper {
    public static IHandler getObject(Class<IHandler> c) {
        try {
            return c.getConstructor().newInstance();
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException
                 | InstantiationException ignored) {
            return null;
        }
    }

    public static boolean checkAndRun(SpeakerRegistry sr, List<IHandler> handlers, String expr) {
        for (IHandler c : handlers) {
            if (c.canTake(expr)) {
                sr.broadcast(c.take(expr));
                return true;
            }
        }

        return false;
    }

    public static boolean checkAndRun(SpeakerRegistry sr, IHandler c, String expr) {
        if (c.canTake(expr)) {
            sr.broadcast(c.take(expr));
            return true;
        }

        return false;
    }
}
