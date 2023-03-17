package pix.commands;

import pix.data.MyData;
import pix.ui.Ui;

/**
 * Parent Command class.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public abstract String execute(MyData data, Ui ui);
}
