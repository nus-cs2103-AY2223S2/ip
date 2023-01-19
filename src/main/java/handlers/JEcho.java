package handlers;

import types.IHandler;

public final class JEcho implements IHandler {
    @Override
    public void take(String s) {
        System.out.println(s);
    }

    @Override
    public boolean canTake(String s) {
        return true;
    }
}
