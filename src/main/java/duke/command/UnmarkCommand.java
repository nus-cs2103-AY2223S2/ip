package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A command that stores the command to unmark a task as not done. The action of adding the task can be carried out
 * when called.
 */
public class UnmarkCommand extends Command {
    /**
     * The string representation of the index to be unmarked as not done.
     */
    private final String INDEX_STRING;

    /**
     * Constructor for a command to unmark a task as not done.
     *
     * @param commandString The unmark command in string representation
     * @param indexString   The string representation of the index to be unmarked as not done
     */
    public UnmarkCommand(String commandString, String indexString) {
        super(AvailableCommands.UNMARK, commandString);
        INDEX_STRING = indexString;
    }

    /**
     * Unmark the task as not done in the task list with the given index.
     * Checks if the index in string representation is valid. If so, unmark the appropriate task as not done.
     * Otherwise, throw an exception that states the issue with the string representation of the index.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> t = tasks.getTasks();
        int index = isValidIndex(INDEX_STRING, t);

        t.get(index).unmarkTask();

        String msgHeader = "I've unmarked this task as not done:";

        ui.showMsg(msgHeader);
        ui.showMsg(t.get(index).toString());
    }
}
