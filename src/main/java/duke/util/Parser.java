package duke.util;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Parser class: handles user inputs
 */
public class Parser {

    /**
     * Enum CommandType to identify different commands
     */
    private enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }

    /**
     * Parse user input into an executable command
     *
     * @param input    User inputs as String
     * @param taskList Existing TaskList
     * @param ui       Instance of Ui
     * @param storage  Instance of Storage
     * @return command
     * @throws DukeException Checks for invalid inputs
     */
    public static Command parse(String input, TaskList taskList, Ui ui, Storage storage)
            throws DukeException {

        checkForEmptyInput(input);
        return convertToCommand(input, taskList, ui, storage);

    }

    /**
     * Handles empty inputs
     *
     * @param input Entire String input from user
     * @throws DukeException If input is empty
     */
    private static void checkForEmptyInput(String input) throws DukeException {
        if (input.trim().isEmpty()) {
            throw new DukeException("Empty Input");
        }
    }

    private static Command convertToCommand(String input, TaskList taskList, Ui ui, Storage storage)
            throws DukeException {
        try {
            String[] inputs = input.split(" ");
            CommandType commandType = CommandType.valueOf(inputs[0].toUpperCase());

            // Removes the commandType string from input
            String filtered_input = filterInput(input);

            switch (commandType) {
            case BYE:
                return new ByeCommand(taskList, ui, storage);

            case LIST:
                return new ListCommand(taskList, ui);

            case MARK:
                return new MarkCommand(filtered_input, taskList, ui, storage);

            case UNMARK:
                return new UnmarkCommand(filtered_input, taskList, ui, storage);

            case DELETE:
                return new DeleteCommand(filtered_input, taskList, ui, storage);

            case TODO:
                return new TodoCommand(filtered_input, taskList, ui, storage);

            case DEADLINE:
                return new DeadlineCommand(filtered_input, taskList, ui, storage);

            case EVENT:
                return new EventCommand(filtered_input, taskList, ui, storage);

            case FIND:
                return new FindCommand(filtered_input, taskList, ui);

            default:
                throw new DukeException("OOPS!!! Something went wrong.");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
        }
    }

    private static String filterInput(String input) {
        String[] result = input.split(" ", 2);

        if (result.length == 1) {
            return "";
        } else {
            return result[1];
        }

    }
}
