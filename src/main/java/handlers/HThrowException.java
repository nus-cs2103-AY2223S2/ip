package handlers;

import types.ICommand;

public class HThrowException implements ICommand {
    @Override
    public void take(String s) {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean canTake(String s) {
        return true;
    }
}
