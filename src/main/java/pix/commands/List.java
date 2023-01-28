package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * List class which displays the list of tasks.
 */
public class List extends Command {
    public void execute(MyData data, Ui ui) {
        ui.list(data);
    }
}
