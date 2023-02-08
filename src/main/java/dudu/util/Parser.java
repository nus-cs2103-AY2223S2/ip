package dudu.util;

import java.util.Arrays;

import dudu.command.ByeCommand;
import dudu.command.Command;
import dudu.command.DeadlineCommand;
import dudu.command.DeleteCommand;
import dudu.command.EventCommand;
import dudu.command.FindCommand;
import dudu.command.IntroCommand;
import dudu.command.ListCommand;
import dudu.command.MarkCommand;
import dudu.command.TodoCommand;
import dudu.command.UnMarkCommand;
import dudu.command.UnknownCommand;
import dudu.exception.DuduException;
import dudu.exception.EmptyDescriptionException;

/**
 * Parser class on parsing the input.
 */
public class Parser {
    private enum Instruction {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        DELETE,
        MARK,
        UNMARK,
        BYE,
        HI,
        FIND
    }

    /**
     * Parse command the input.
     * @param input The user input.
     * @return Parsed command
     * @throws DuduException If the command is not recognised.
     */
    public static Command parse(String input) throws DuduException {
        assert input != null;
        String[] inputArr = input.split(" ");
        boolean isUnknownCommand = Arrays.stream(Instruction.values())
                .map(x -> x.toString())
                .noneMatch(x -> x.equals(inputArr[0].toUpperCase()));
        if (isUnknownCommand) {
            return new UnknownCommand("unknown");
        }
        Instruction instruction = Instruction.valueOf(inputArr[0].toUpperCase());
        switch (instruction) {
        case HI:
            return new IntroCommand();
        case TODO:
            if (input.trim().length() == 4) {
                throw new EmptyDescriptionException("todo", "description", "Missing task description");
            }
            return new TodoCommand(input.substring(5));
        case DEADLINE:
            if (input.trim().length() == 8) {
                throw new EmptyDescriptionException("deadline", "description", "Missing task description");
            }
            return new DeadlineCommand(input.substring(9));
        case EVENT:
            if (input.trim().length() == 5) {
                throw new EmptyDescriptionException("event", "description", "Missing task description");
            }
            return new EventCommand(input.substring(6));
        case LIST:
            return new ListCommand();
        case DELETE:
            if (inputArr.length == 1) {
                throw new EmptyDescriptionException("delete command", "description", "Missing description");
            }
            return new DeleteCommand(inputArr[1]);
        case MARK:
            if (inputArr.length == 1) {
                throw new EmptyDescriptionException("mark command", "description", "Missing description");
            }
            return new MarkCommand(inputArr[1]);
        case UNMARK:
            if (inputArr.length == 1) {
                throw new EmptyDescriptionException("unmark command", "description", "Missing description");
            }
            return new UnMarkCommand(inputArr[1]);
        case BYE:
            return new ByeCommand();
        default:
            if (input.trim().length() == 4) {
                throw new EmptyDescriptionException("find", "description", "Missing task description");
            }
            return new FindCommand(input.substring(5));
        }
    }
}
