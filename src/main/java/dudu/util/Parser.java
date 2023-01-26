package dudu.util;

import dudu.command.*;
import dudu.exception.DuduException;
import dudu.exception.EmptyDescriptionException;
import dudu.exception.InvalidCommandException;
import dudu.task.TaskList;

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
            throw new InvalidCommandException(ex.getMessage());
        }
    }
}
