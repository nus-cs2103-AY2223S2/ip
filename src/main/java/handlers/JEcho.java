package handlers;

import types.IHandler;

/**
 * Repeats user input.
 */
public final class JEcho implements IHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public String take(String s) {
        return s;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canTake(String s) {
        return true;
    }
}
