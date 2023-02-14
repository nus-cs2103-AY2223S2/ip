package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.textui.TextUi;


/**
 * A command that stores the command to delete a task. The action of deleting the task can be carried out when called.
 */
public class DeleteCommand extends Command {
    /**
     * The string representation of the index to be deleted.
     */
    private final String indexString;

    /**
     * Constructor for a command to delete a task.
     *
     * @param indexString The string representation of the index to be deleted
     */
    public DeleteCommand(String indexString) {
        super(AvailableCommands.DELETE);
        this.indexString = indexString;
    }

    /**
     * Deletes the task in the task list with the given index.
     * Checks if the index in string representation is valid. If so, deletes the appropriate task. Otherwise, throw
     * an exception that states the issue with the string representation of the index.
     *
     * @param taskList List of tasks that are stored
     * @param ui       UI to deal with the visual output
     * @param storage  Storage to deal with input and output of data
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        int index = isValidIndex(indexString, tasks);

        Task task = tasks.get(index);
        tasks.remove(index);

        return ui.showDeleteTask(task.toString(), tasks.size());
    }
}
