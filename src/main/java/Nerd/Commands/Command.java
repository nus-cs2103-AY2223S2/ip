package Nerd.Commands;

import Nerd.entities.TaskList;
import Nerd.Ui.Ui;


/**
 * Represents the Duke.Commands.Command abstract class.
 * Several commands inherit from Command, and instantiating this
 * will create the command required for processing.
 */
public abstract class Command {

    /**
     * Duke.Commands abstract method that processes the command.
     *
     * @param list The tasklist of the Nerdbot.
     * @param ui   The user interface of the Nerdbot.
     */
    public abstract String processCommand(TaskList list, Ui ui);
}
