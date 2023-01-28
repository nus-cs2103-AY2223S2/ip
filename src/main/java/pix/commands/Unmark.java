package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

public class Unmark extends Command {
    /** Index to remove mark. */
    private final int id;

    /**
     * Constructs a new Unmark command.
     *
     * @param id Index to remove mark.
     */
    public Unmark(int id) {
        this.id = id;
    }

    public void execute(MyData data, Ui ui) {
        data.markUndone(this.id);
        data.saveToFile();
        ui.unmark(data.getData(this.id));
    }
}

