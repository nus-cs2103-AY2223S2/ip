package Nerd.Commands;

import Nerd.Ui.Ui;
import Nerd.entities.TaskList;

/**
 * Represents the Duke.Commands.Command abstract class.
 * Several commands inherit from Command, and instantiating this
 * will create the command required for processing.
 */
public abstract class Command {

    /**
     * Duke.Commands abstract method that processes the command.
     */
    public void processCommand(TaskList list, int index, Ui ui) {};
    public void processCommand(TaskList list, String desc, String by, Ui ui) {};
    public void processCommand(TaskList list, String desc, String from, String to, Ui ui) {};
    public void processCommand(Ui ui) {};
    public void processCommand(TaskList list, String item, Ui ui) {};

}
