package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * List class which displays the list of tasks.
 */
public class List extends Command {
    public String execute(MyData data, Ui ui) {
        return ui.list(data);
    }
}
