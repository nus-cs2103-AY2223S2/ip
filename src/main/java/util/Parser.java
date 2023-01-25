package util;

import command.*;
import exception.DukeException;
import task.TaskList;

public class Parser {

    private enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }
    public static Command parse(String input, TaskList taskList, Ui ui, Storage storage)
            throws DukeException {

        String[] inputs = input.split(" ");
        CommandType commandType = CommandType.valueOf(inputs[0].toUpperCase());

        switch(commandType) {
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

            default :
                throw new DukeException("☹ OOPS!!! Something went wrong.");
        }
    }
}
