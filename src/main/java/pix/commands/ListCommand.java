package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * ListCommand class which displays the list of tasks.
 */
public class ListCommand extends Command {
    public String execute(MyData data, Ui ui) {
        return ui.list(data);
    }
}
