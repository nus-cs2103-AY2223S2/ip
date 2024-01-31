package handlers;

import types.IHandler;

/**
 * Throws the exception that user input cannot be recognized.
 */
public class JThrowException implements IHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public String take(String s) {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canTake(String s) {
        return true;
    }
}
