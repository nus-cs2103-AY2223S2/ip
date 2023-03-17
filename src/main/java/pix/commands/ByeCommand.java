package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * ByeCommand class which exits pix.
 */
public class ByeCommand extends Command {
    public String execute(MyData data, Ui ui) {
        return ui.bye();
    }
}
