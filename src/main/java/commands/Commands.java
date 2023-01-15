package commands;

import data.MyData;

public abstract class Commands {
    public abstract void execute(MyData data);

    public String getLine() {
        return "    ____________________________________________________________\n";
    }
}
