package handlers;

import types.ICommand;

import java.util.regex.Pattern;

public class ETodoEmptyDescription implements ICommand {
    private static final Pattern p = Pattern.compile("todo");

    @Override
    public void take(String s) {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}
