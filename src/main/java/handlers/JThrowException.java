package handlers;

import types.IHandler;

public class JThrowException implements IHandler {
    @Override
    public String take(String s) {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    @Override
    public boolean canTake(String s) {
        return true;
    }
}
