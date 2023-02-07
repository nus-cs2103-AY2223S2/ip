package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * HelpCommand class which provides user list of commands to use Pix.
 */
public class HelpCommand extends Command {
    public String execute(MyData data, Ui ui) {
        return ui.help();
    }
}
