package commands;

import exception.TreeBotException;
import tasks.TaskList;
import utils.Storage;



public abstract class Command {

    /**
     * Executes a Command.
     * @param taskList
     * @param storage
     * @throws TreeBotException
     */
    public abstract String execute(TaskList taskList, Storage storage) ;

    abstract String toResultString();
}
