package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

public class List extends Command {
    public void execute(MyData data, Ui ui) {
        ui.list(data);
    }
}
