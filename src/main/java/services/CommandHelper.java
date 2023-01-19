package services;

import types.ICommand;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class CommandHelper {
    @SuppressWarnings("unchecked")
    public static Class<ICommand> getClass(String s) {
        try {
            return (Class<ICommand>) Class.forName(s);
        } catch (ClassNotFoundException e) {
            System.out.printf("[Command Registration] command %s not found!\n", s);
            return null;
        }
    }

    public static ICommand getObject(Class<ICommand> c) {
        try {
            return c.getConstructor().newInstance();
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException
                 | InstantiationException ignored) {
            return null;
        }
    }

    public static boolean checkAndRun(List<ICommand> handlers, String expr) {
        for (ICommand c : handlers) {
            if (c.canTake(expr)) {
                c.take(expr);
                return true;
            }
        }

        return false;
    }

    public static boolean checkAndRun(ICommand c, String expr) {
        if (c.canTake(expr)) {
            c.take(expr);
            return true;
        }

        return false;
    }
}
