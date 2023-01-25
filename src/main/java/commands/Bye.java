package commands;

import data.MyData;
import ui.Ui;

public class Bye extends Command {
    public void execute(MyData data, Ui ui) {
        ui.bye();
    }
}
