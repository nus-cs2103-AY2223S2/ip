package handlers;

import types.IHandler;

/**
 * Command to repeat user input.
 */
public final class JEcho implements IHandler {
    @Override
    public String take(String s) {
        return s;
    }

    @Override
    public boolean canTake(String s) {
        return true;
    }
}
