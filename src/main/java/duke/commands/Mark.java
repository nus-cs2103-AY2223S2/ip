package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

public class Mark extends Command {
    private final int id;

    public Mark(int id) {
        this.id = id;
    }

    public void execute(MyData data, Ui ui) {
        data.markDone(this.id);
        data.saveToFile();
        ui.mark(data.getData(this.id));
    }
}

