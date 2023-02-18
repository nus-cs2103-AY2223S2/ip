package hachi.commands;

import hachi.main.TaskList;
import hachi.main.Storage;
import hachi.main.Ui;

/**
 * Encapsulates the type of command
 */
public abstract class Command {

    /**
     * Executes the command by user
     *
     * @param tasks   To-do list
     * @param ui      Ui object
     * @param storage storage object that stores the to-do list
     * @return True if execution is successful, false if it is not.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}