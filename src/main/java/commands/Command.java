package commands;

import exception.TreeBotException;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;



public abstract class Command {

    /**
     * Executes a Command.
     * @param taskList
     * @param ui
     * @param storage
     * @throws TreeBotException
     */
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws TreeBotException;
}
