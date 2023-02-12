package panav.command;

import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;

/**
 * Class to encapsulate a 'list' command, extends from Command.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Specifies the behaviour of 'list' command when called to execute. It prints
     * all the tasks in the whole list.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.printAllTasks();
    }

    @Override
    public String toString() {
        return ListCommand.COMMAND_WORD;
    }
}
