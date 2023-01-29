package duke.parser;

import java.util.regex.Pattern;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.enums.CommandType;
import duke.exceptions.DukeException;


/**
 * Parser class to parse input command.
 */
public class Parser {

    /**
     * Parses an input command.
     * @param fullCommand Input command to be parsed.
     * @return One of the specified Command objects.
     * @throws DukeException If command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.split(" ", 2);
        String commandType = arr[0].toLowerCase();
        if (commandType.equals(CommandType.BYE.toString())) {
            return new ByeCommand();
        } else if (commandType.equals(CommandType.LIST.toString())) {
            return new ListCommand();
        } else if (commandType.equals(CommandType.MARK.toString())) {
            if (arr.length == 1) {
                throw CommandType.MARK.getErr();
            }
            return new MarkCommand(arr[1]);
        } else if (commandType.equals(CommandType.UNMARK.toString())) {
            if (arr.length == 1) {
                throw CommandType.UNMARK.getErr();
            }
            return new UnmarkCommand(arr[1]);
        } else if (commandType.equals(CommandType.DELETE.toString())) {
            if (arr.length == 1) {
                throw CommandType.DELETE.getErr();
            }
            return new DeleteCommand(arr[1]);
        } else if (commandType.equals(CommandType.FIND.toString())) {
            if (arr.length == 1) {
                throw CommandType.FIND.getErr();
            }
            return new FindCommand(arr[1]);
        } else if (commandType.equals(CommandType.TODO.toString())) {
            if (arr.length == 1) {
                throw CommandType.TODO.getErr();
            }
            return new TodoCommand(arr[1]);
        } else if (commandType.equals(CommandType.DEADLINE.toString())) {
            if (arr.length == 1) {
                throw CommandType.DEADLINE.getErr();
            }
            return prepareDeadline(arr[1]);
        } else if (commandType.equals(CommandType.EVENT.toString())) {
            if (arr.length == 1) {
                throw CommandType.EVENT.getErr();
            }
            return prepareEvent(arr[1]);
        } else {
            return new IncorrectCommand();
        }
    }

    /**
     * Parses arguments in the context of the DeadlineCommand.
     */
    private static DeadlineCommand prepareDeadline(String message) throws DukeException {
        Pattern p = Pattern.compile("/by");
        String[] temp = p.split(message);
        if (temp.length < 2) {
            throw CommandType.DEADLINE.getErr();
        }
        String desc = temp[0].trim();
        String by = temp[1].trim();
        return new DeadlineCommand(desc, by);
    }

    /**
     * Parses arguments in the context of the EventCommand.
     */
    private static EventCommand prepareEvent(String message) throws DukeException {
        Pattern p1 = Pattern.compile("/from");
        String[] temp1 = p1.split(message);
        if (temp1.length < 2) {
            throw CommandType.EVENT.getErr();
        }
        String desc = temp1[0].trim();
        Pattern p2 = Pattern.compile("/to");
        String[] temp2 = p2.split(temp1[1]);
        if (temp2.length < 2) {
            throw CommandType.EVENT.getErr();
        }
        String from = temp2[0].trim();
        String to = temp2[1].trim();
        return new EventCommand(desc, from, to);
    }
}
