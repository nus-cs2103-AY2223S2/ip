package handlers;

import types.IHandler;

import java.util.regex.Pattern;

public final class JBye implements IHandler {
    private static final Pattern p = Pattern.compile("bye");
    private static final String outputText = "Bye. Hope to see you again soon!";

    @Override
    public void take(String s) {
        System.out.println(outputText);
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}
