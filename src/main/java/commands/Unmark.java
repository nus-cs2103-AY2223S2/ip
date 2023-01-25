package commands;

import data.MyData;
import ui.Ui;

public class Unmark extends Command {
    private final int id;

    public Unmark(int id) {
        this.id = id;
    }

    public void execute(MyData data, Ui ui) {
        data.markUndone(this.id);
        data.saveToFile();
        ui.unmark(data.getData(this.id));
    }
}

