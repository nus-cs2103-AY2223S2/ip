package Commands;

import types.ICommand;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class Echo implements ICommand {
    public static final Pattern p = Pattern.compile(".*");

    @Override
    public void take(String s) {
        System.out.println(s);
    }

    @Override
    public boolean canTake(String s) {
        return p.matcher(s).matches();
    }
}
