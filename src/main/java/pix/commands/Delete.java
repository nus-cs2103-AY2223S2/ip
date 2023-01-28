package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

public class Delete extends Command {
    /** Index to delete task. */
    private final int id;

    /**
     *  Constructs a new Delete command.
     *
     * @param id Index to delete task.
     */
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