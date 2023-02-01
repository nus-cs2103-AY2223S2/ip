package dudu.util;

import dudu.command.ByeCommand;
import dudu.command.Command;
import dudu.command.DeadlineCommand;
import dudu.command.DeleteCommand;
import dudu.command.EventCommand;
import dudu.command.FindCommand;
import dudu.command.ListCommand;
import dudu.command.MarkCommand;
import dudu.command.TodoCommand;
import dudu.command.UnMarkCommand;
import dudu.command.UnknownCommand;
import dudu.exception.DuduException;
import dudu.exception.EmptyDescriptionException;
import dudu.task.TaskList;

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
        FIND
    }

    /**
     * Parse command the input.
     * @param input The user input.
     * @param list list to store the task.
     * @param storage store the list
     * @return Parsed command
     * @throws DuduException If the command is not recognised.
     */
    public static Command parse(String input, TaskList list, Storage storage) throws DuduException {
        try {
            String[] inputArr = input.split(" ");
            Instruction instruction = Instruction.valueOf(inputArr[0].toUpperCase());
            switch (instruction) {
            case TODO:
                if (input.trim().length() == 4) {
                    throw new EmptyDescriptionException("todo", "description", "Missing task description");
                }
                return new TodoCommand(input.substring(5)).execute(list, storage);
            case DEADLINE:
                if (input.trim().length() == 8) {
                    throw new EmptyDescriptionException("deadline", "description", "Missing task description");
                }
                return new DeadlineCommand(input.substring(9)).execute(list, storage);
            case EVENT:
                if (input.trim().length() == 5) {
                    throw new EmptyDescriptionException("event", "description", "Missing task description");
                }
                return new EventCommand(input.substring(6)).execute(list, storage);
            case LIST:
                return new ListCommand().execute(list, storage);
            case DELETE:
                return new DeleteCommand(inputArr[1]).execute(list, storage);
            case MARK:
                return new MarkCommand(inputArr[1]).execute(list, storage);
            case UNMARK:
                return new UnMarkCommand(inputArr[1]).execute(list, storage);
            case BYE:
                return new ByeCommand().execute(list, storage);
            default:
                if (input.trim().length() == 4) {
                    throw new EmptyDescriptionException("find", "description", "Missing task description");
                }
                return new FindCommand(input.substring(5)).execute(list, storage);
            }
        } catch (IllegalArgumentException ex) {
            return new UnknownCommand(ex.getMessage()).execute(list, storage);
        }
    }
}
