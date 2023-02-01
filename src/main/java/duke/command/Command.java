package duke.command;

import java.util.ArrayList;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.textui.TextUi;


/**
 * A Command stores the command, together with the command string. The appropriate action can be called subsequently
 * that correspond towards the actions specified.
 */
public class Command {
    /**
     * The command stored.
     */
    private final AvailableCommands CURRENT_COMMAND;
    /**
     * The string representation of the command stored.
     */
    private final String COMMAND_STRING;

    /**
     * The list of commands that are available for use.
     */

    public enum AvailableCommands {
        EXIT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        ADD_TODO,
        ADD_DEADLINE,
        ADD_EVENT,
        FIND
    }

    /**
     * Constructor for a command object.
     *
     * @param currentCommand Command being stored that needs to be carried out
     * @param commandString  String representation of the command stored
     */
    public Command(AvailableCommands currentCommand, String commandString) {
        CURRENT_COMMAND = currentCommand;
        COMMAND_STRING = commandString;
    }

    /**
     * Executes the command being stored.
     *
     * @param taskList List of tasks that are stored
     * @param ui       UI to deal with the visual output
     * @param storage  Storage to deal with input and output of data
     */
    public String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException {
        return ui.showError(String.format("Error processing %s command", COMMAND_STRING));
    }

    /**
     * Checks whether it is the exit command.
     *
     * @return Boolean result on whether it is the exit command
     */
    public boolean isExit() {
        return CURRENT_COMMAND == AvailableCommands.EXIT;
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
        Pattern pattern = Pattern.compile("^[0-9]+$");
        boolean isNumber = pattern.matcher(indexStr).matches();

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
