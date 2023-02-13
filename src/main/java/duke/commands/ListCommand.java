package duke.commands;

import java.util.ArrayList;

import duke.taskers.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * List Command.
 */
public class ListCommand extends Command {


    public static final String COMMAND_WORD = "list";

    /**
     * Constructor.
     */
    public ListCommand() {

    }

    /**
     * Executes the task.
     *
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     * @return String response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> allTasks = taskList.getList();
        return ui.showList(allTasks);
    }

    /**
     * Checks if the command exits.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

