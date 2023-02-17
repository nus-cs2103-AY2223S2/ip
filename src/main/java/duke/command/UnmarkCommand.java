package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.textui.TextUi;

/**
 * A command that stores the command to unmark a task as not done.
 * The action of unmarking the task can be carried out when called.
 */
public class UnmarkCommand extends Command {
    /**
     * The string representation of the index to be unmarked as not done.
     */
    private final String indexString;

    /**
     * Constructor for a command to unmark a task as not done.
     *
     * @param indexString The string representation of the index to be unmarked as
     *                    not done
     */
    public UnmarkCommand(String indexString) {
        super(AvailableCommands.UNMARK);
        this.indexString = indexString;
    }

    /**
     * Unmark the task as not done in the task list with the given index.
     * Checks if the index in string representation is valid. If so, unmark the appropriate task as not done.
     * Otherwise, throw an exception that states the issue with the string representation of the index.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     * @return The string of what is printed out after execution
     * @throws DukeException If index is invalid
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException {
        ArrayList<Task> t = tasks.getTasks();
        int index = isValidIndex(indexString, t);

        t.get(index).unmarkTask();

        String msgHeader = "I've unmarked this task as not done:";

        String output = ui.showMsg(msgHeader);
        output += "\n";
        output += ui.showMsg(t.get(index).toString());

        return output;
    }
}
