package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A command that stores the command to delete a task. The action of adding the task can be carried out when called.
 */
public class DeleteCommand extends Command {
    /**
     * The string representation of the index to be deleted.
     */
    private final String INDEX_STRING;

    /**
     * Constructor for a command to delete a task.
     *
     * @param commandString The delete command in string representation
     * @param INDEX_STRING The string representation of the index to be deleted
     */
    public DeleteCommand(String commandString, String INDEX_STRING) {
        super(Commands.DELETE, commandString);
        this.INDEX_STRING = INDEX_STRING;
    }

    /**
     * Deletes the task in the task list with the given index.
     * Checks if the index in string representation is valid. If so, deletes the appropriate task. Otherwise, throw
     * an exception that states the issue with the string representation of the index.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTasks();
        int index = this.isValidIndex(this.INDEX_STRING, t);

        Task task = t.get(index);
        t.remove(index);

        ui.showDeleteTask(task.toString(), t.size());
    }
}
