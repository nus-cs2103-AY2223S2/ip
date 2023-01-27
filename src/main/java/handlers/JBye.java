package handlers;

import types.IHandler;

import java.util.regex.Pattern;

public final class JBye implements IHandler {
    private static final Pattern PATTERN = Pattern.compile("bye");
    private static final String outputText = "Bye. Hope to see you again soon!\n";

    @Override
    public String take(String s) {
        return outputText;
    }

    @Override
    public boolean canTake(String s) {
        return PATTERN.matcher(s).matches();
    }
}
