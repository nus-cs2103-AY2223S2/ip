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
            throw new DukeException("Where is your IQ? Wait, I mean your input.");
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
                throw new DukeException("Something went wrong.");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm afraid for the future of this country if you can't make yourself understood.");
        }
    }

    private static String filterInput(String input) throws DukeException {
        String[] result = input.split(" ", 2);
        CommandType commandType = CommandType.valueOf(result[0].toUpperCase());

        if (result.length == 1) {
            switch (commandType) {
            case MARK:
                throw new DukeException("I hate to tell you, but your mark syntax is wrong!");

            case UNMARK:
                throw new DukeException("I hate to tell you, but your unmark syntax is wrong!");

            case DELETE:
                throw new DukeException("I hate to tell you, but your delete syntax is wrong!");

            case SEEK:
                throw new DukeException("I hate to tell you, but your seek syntax is wrong!");

            case TODO:
                throw new DukeException("Where is you Todo Name? You're disgusting!");

            case DEADLINE:
                throw new DukeException("Where is you Deadline Name? You're disgusting!");

            case EVENT:
                throw new DukeException("Where is you Event Name? You're disgusting!");

            case FIND:
                throw new DukeException("O-M-G! You are missing the keyword.");

            default:
                return ""; // Will not reach here
            }
        } else {
            return result[1];
        }

    }
}
