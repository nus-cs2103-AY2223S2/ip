package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A Command stores the command, together with the command string. The appropriate action can be called subsequently
 * that correspond towards the actions specified.
 */
public class Command {
    /**
     * The command stored.
     */
    private final Commands COMMAND;
    /**
     * The string representation of the command stored.
     */
    private final String COMMAND_STRING;

    /**
     * The list of commands that are available for use.
     */
    public enum Commands {
        EXIT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        ADD_TODO,
        ADD_DEADLINE,
        ADD_EVENT
    }

    /**
     * Constructor for a command object.
     *
     * @param COMMAND        Command being stored that needs to be carried out
     * @param COMMAND_STRING String representation of the command stored
     */
    public Command(Commands COMMAND, String COMMAND_STRING) {
        this.COMMAND = COMMAND;
        this.COMMAND_STRING = COMMAND_STRING;
    }

    /**
     * Executes the command being stored.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(String.format("Error processing %s command", this.COMMAND_STRING));
    }

    /**
     * Checks whether it is the exit command.
     *
     * @return Boolean result on whether it is the exit command
     */
    public boolean isExit() {
        return COMMAND == Commands.EXIT;
    }

    /**
     * Check whether the string representation of the index is an integer. If it is, check again whether it is within
     * the bounds of the list of tasks stored. Then, return the valid integer representation of the index. Otherwise,
     * throw an exception stating the issue with the string representation of the index.
     *
     * @param indexStr String representation of the index being checked
     * @param tasks    List of tasks being referenced upon
     * @return The integer representation of the index that was in string representation
     * @throws DukeException If the string representation of index is not an integer or out of bounds of task list
     */
    protected int isValidIndex(String indexStr, ArrayList<Task> tasks) throws DukeException {
        Pattern p = Pattern.compile("^[0-9]+$");
        boolean isNumber = p.matcher(indexStr).matches();

        if (!isNumber) {
            throw new DukeException("Index provided is not an integer.");
        }

        int index = Integer.parseInt(indexStr) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bounds of tasks list.");
        }

        return index;
    }
}
