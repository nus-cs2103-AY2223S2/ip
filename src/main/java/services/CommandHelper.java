package services;

import types.ICommand;

import java.lang.reflect.InvocationTargetException;

public class CommandHelper {
    public static boolean canTake(Class<ICommand> c, String s) {
        try {
            return (boolean) c.getMethod("canTake", String.class).invoke(c.getConstructor().newInstance(), s);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException |
                 InstantiationException ignored) {
        }
        return false;
    }

    public static void take(Class<ICommand> c, String s) {
        try {
            c.getMethod("take", String.class).invoke(c.getConstructor().newInstance(), s);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException |
                 InstantiationException ignored) {
        }
    }

    @SuppressWarnings("unchecked")
    public static Class<ICommand> fromName(String s) {
        try {
            return (Class<ICommand>) Class.forName(s);
        } catch (ClassNotFoundException e) {
            System.out.printf("[Command Registration] command %s not found!\n", s);
            return null;
        }
    }
}
