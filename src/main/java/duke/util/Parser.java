package duke.util;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Parser class handles user inputs.
 *
 * @author Guo-KeCheng
 */
public class Parser {

    /**
     * Enum CommandType to identify different commands.
     */
    private enum CommandType {
        BYE,
        LIST,
        SORT,
        SEEK,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }

    /**
     * Parse user input into an executable command.
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

        checkForEmptyInput(input.trim());
        return convertToCommand(input.trim(), taskList, ui, storage);

    }

    /**
     * Handles empty inputs.
     *
     * @param input Entire String input from user
     * @throws DukeException If input is empty
     */
    private static void checkForEmptyInput(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Empty Input");
        }
    }

    private static Command convertToCommand(String input, TaskList taskList, Ui ui, Storage storage)
            throws DukeException {
        try {
            String[] inputs = input.split(" ");
            CommandType commandType = CommandType.valueOf(inputs[0].toUpperCase());

            // Filter out any invalid inputs to always instantiate command with correct inputs
            String filtered_input = filterInput(input);

            switch (commandType) {
            case BYE:
                return new ByeCommand(taskList, ui, storage);

            case LIST:
                return new ListCommand(taskList, ui);

            case SORT:
                return new SortCommand(taskList, ui, storage);

            case SEEK:
                return new SeekCommand(filtered_input, taskList, ui, storage);

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

    private static String filterInput(String input) throws DukeException {
        String[] result = input.split(" ", 2);
        CommandType commandType = CommandType.valueOf(result[0].toUpperCase());

        if (result.length == 1) {
            switch (commandType) {
            case MARK:
                throw new DukeException("Incorrect command: mark <valid task index>");

            case UNMARK:
                throw new DukeException("Incorrect command: unmark <valid task index>");

            case DELETE:
                throw new DukeException("Incorrect command: delete <valid task index>");

            case SEEK:
                throw new DukeException("Incorrect command: seek <valid duration>");

            case TODO:
                throw new DukeException("OOPS!!! Missing Todo Name.");

            case DEADLINE:
                throw new DukeException("OOPS!!! Missing Deadline Name.");

            case EVENT:
                throw new DukeException("OOPS!!! Missing Event Name.");

            case FIND:
                throw new DukeException("OOPS!!! Missing Keyword.");

            default:
                return "";
            // Will not reach here
            }
        } else {
            return result[1];
        }

    }
}
