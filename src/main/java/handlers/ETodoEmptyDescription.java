package handlers;

import java.util.regex.Pattern;

import types.IHandler;

/**
 * Exception class for empty description..
 */
public class ETodoEmptyDescription implements IHandler {
    private static final Pattern PATTERN = Pattern.compile("todo");

    @Override
    public String take(String s) {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    @Override
    public boolean canTake(String s) {
        return PATTERN.matcher(s).matches();
    }
}
