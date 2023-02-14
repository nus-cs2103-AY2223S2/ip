package duke.command;

import duke.DukeException;
import duke.task.TaskList;

/**
 * Contains static methods to validate the inputs of user input commands
 */
public class CommandValidations {

    /**
     * Validates the presence of args after a command.
     * Does not precisely check validity of args for the command.
     * @param cmd User command.
     * @throws DukeException Exception thrown if no args were given.
     */
    public static void validateNotEmptyArgs(String cmd) throws DukeException {
        if (cmd.split(" ").length <= 1) {
            throw new DukeException("You did not specify any details...");
        }
    }

    /**
     * Validates that a task description was given.
     * @param cmd User command.
     * @throws DukeException Exception thrown if no args were given or task description is empty.
     */
    public static void validateHasTaskDescription(String cmd) throws DukeException {
        String[] args = cmd.split(" ");
        if (args.length <= 1 || args[1].charAt(0) == '/') {
            throw new DukeException("What is a task without a description good for?");
        }
    }

    /**
     * Validates that index is within current set of tasks.
     * @param index Index of the task, 0-indexed.
     * @throws DukeException Exception thrown if index is invalid.
     */
    public static void validateTaskIndex(Integer index, TaskList taskList) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("No such task!");
        }
    }

    /**
     * Validates that a parameter has been provided by the user.
     * @param cmd The command given by the user.
     * @param param The mentioned parameter that must exist.
     * @throws DukeException Exception thrown if the parameter is not provided.
     */
    public static void validateParameterExists(String cmd, String param) throws DukeException {
        if (cmd.split(" " + param + " ").length < 2) {
            throw new DukeException(String.format("Parameter %s not given...", param));
        }
    }
}
