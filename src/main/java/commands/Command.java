package commands;

import data.MyData;

public abstract class Command {
    public abstract void execute(MyData data);
}
