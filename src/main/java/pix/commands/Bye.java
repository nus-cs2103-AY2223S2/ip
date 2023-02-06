package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * Bye class which exits pix.
 */
public class Bye extends Command {
    public String execute(MyData data, Ui ui) {
        return ui.bye();
    }
}
