package commands;

import data.MyData;
import ui.Ui;

public abstract class Command {
    public abstract void execute(MyData data, Ui ui);
}
