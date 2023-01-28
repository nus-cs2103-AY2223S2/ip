package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * Bye class which exits pix.
 */
public class Bye extends Command {
    public void execute(MyData data, Ui ui) {
        ui.bye();
    }
}
