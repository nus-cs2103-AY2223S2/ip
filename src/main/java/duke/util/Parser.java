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

        try {
            String[] inputs = input.split(" ");
            CommandType commandType = CommandType.valueOf(inputs[0].toUpperCase());

            switch (commandType) {
            case BYE:
                return new ByeCommand(taskList, ui, storage);

            case LIST:
                return new ListCommand(taskList, ui);

            case MARK:
                return new MarkCommand(input, taskList, ui);

            case UNMARK:
                return new UnmarkCommand(input, taskList, ui);

            case DELETE:
                return new DeleteCommand(input, taskList, ui);

            case TODO:
                return new TodoCommand(input, taskList, ui);

            case DEADLINE:
                return new DeadlineCommand(input, taskList, ui);

            case EVENT:
                return new EventCommand(input, taskList, ui);

            case FIND:
                return new FindCommand(input, taskList, ui);

            default:
                throw new DukeException("☹ OOPS!!! Something went wrong.");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
