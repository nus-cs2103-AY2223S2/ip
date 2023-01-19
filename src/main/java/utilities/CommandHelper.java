package utilities;

import types.IHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class CommandHelper {
    @SuppressWarnings("unchecked")
    public static Class<IHandler> getClass(String s) {
        try {
            return (Class<IHandler>) Class.forName(s);
        } catch (ClassNotFoundException e) {
            System.out.printf("[Command Registration] command %s not found!\n", s);
            return null;
        }
    }

    public static IHandler getObject(Class<IHandler> c) {
        try {
            return c.getConstructor().newInstance();
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException
                 | InstantiationException ignored) {
            return null;
        }
    }

    public static boolean checkAndRun(List<IHandler> handlers, String expr) {
        for (IHandler c : handlers) {
            if (c.canTake(expr)) {
                c.take(expr);
                return true;
            }
        }

        return false;
    }

    public static boolean checkAndRun(IHandler c, String expr) {
        if (c.canTake(expr)) {
            c.take(expr);
            return true;
        }

        return false;
    }
}
