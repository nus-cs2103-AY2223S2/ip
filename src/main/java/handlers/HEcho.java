package handlers;

import types.ICommand;

@SuppressWarnings("unused")
public final class HEcho implements ICommand {

    @Override
    public void take(String s) {
        System.out.println(s);
    }

    @Override
    public boolean canTake(String s) {
        return true;
    }
}
