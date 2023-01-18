package services;

import types.ICommand;

import java.lang.reflect.InvocationTargetException;

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
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException |
                 InstantiationException ignored) {
            return null;
        }
    }
}
