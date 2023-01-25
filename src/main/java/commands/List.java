package commands;

import data.MyData;
import ui.Ui;

public class List extends Command {
    public void execute(MyData data, Ui ui) {
        ui.list(data);
    }
}
