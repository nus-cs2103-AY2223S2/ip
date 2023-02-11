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
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) ;

    abstract String toResultString();
}
