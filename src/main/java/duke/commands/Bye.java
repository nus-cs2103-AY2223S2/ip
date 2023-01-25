package duke.commands;

import duke.data.MyData;
import duke.ui.Ui;

public class Bye extends Command {
    public void execute(MyData data, Ui ui) {
        ui.bye();
    }
}
