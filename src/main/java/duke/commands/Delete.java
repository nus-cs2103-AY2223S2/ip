package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

public class Delete extends Command {
    private final int id;

    public Delete(int id) {
        this.id = id;
    }

    public void execute(MyData data, Ui ui) {
        int itemCount = data.len() - 1;
        ui.delete(data.getData(this.id), itemCount);
        data.deleteData(id);
        data.saveToFile();
    }
}