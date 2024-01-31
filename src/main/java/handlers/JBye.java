package handlers;

import java.util.regex.Pattern;

import types.IHandler;

/**
 * Command to handle goodbye.
 */
public final class JBye implements IHandler {
    private static final Pattern PATTERN = Pattern.compile("bye");
    private static final String outputText = "Bye. Hope to see you again soon!\n";

    /**
     * {@inheritDoc}
     */
    @Override
    public String take(String s) {
        return outputText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canTake(String s) {
        return PATTERN.matcher(s).matches();
    }
}
