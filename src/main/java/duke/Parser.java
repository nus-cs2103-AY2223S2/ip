package duke;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.CommandType;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * Represents the Parser that helps to parse input commands
 * into recognisable commands that Sirius can execute
 */
public class Parser {

    /**
     * Returns the CommandType based on the String input given
     * by iterating through the existing CommandType values
     *
     * @return the CommandType based on the String input given
     */
    public static CommandType getCommandType(String input) {
        for (CommandType c : CommandType.values()) {
            if (c.isEqual(input)) {
                return c;
            }
        }
        return CommandType.UNKNOWN;
    }

    /**
     * Returns a boolean indicating if the description is empty or not
     *
     * @param arr an array representation of the user's input line
     * @return if the description given by the user is empty
     */
    public static boolean isDescriptionEmpty(String[] arr) {
        return arr.length == 1 || arr[1].trim().isEmpty();
    }

    /**
     * Parses the user's input and returns a command that is recognised and
     * executable by Sirius. It will also handle unknown commands by throwing a
     * DukeException
     *
     * @param input User's input from the scanner
     * @return A command that represents the input
     * @throws DukeException if the input command is not recognised
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        CommandType commandType = getCommandType(inputArr[0]);
        int index = 0;
        String taskInput = "";

        switch (commandType) {
        case LIST:
            return new ListCommand();
        case MARK:
        case UNMARK:
            index = Integer.parseInt(inputArr[1]) - 1;
            return new MarkCommand(commandType, index);
        case DELETE:
            index = Integer.parseInt(inputArr[1]) - 1;
            return new DeleteCommand(index);
        case TODO:
            if (isDescriptionEmpty(inputArr)) {
                throw new DukeException("Hey now.. The description of a todo cannot be empty. >:(");
            }
            taskInput = inputArr[1];
            Todo todo = new Todo(taskInput);
            return new AddCommand(CommandType.TODO, todo);
        case DEADLINE:
            if (isDescriptionEmpty(inputArr)) {
                throw new DukeException("Hey now.. The description of a deadline cannot be empty. >:(");
            }
            taskInput = inputArr[1];
            String[] deadlineInputArr = taskInput.split("/by", 2);
            String deadlineDesc = deadlineInputArr[0].trim();
            String by = deadlineInputArr[1].trim();
            Deadline deadline = new Deadline(deadlineDesc, by);
            return new AddCommand(CommandType.DEADLINE, deadline);
        case EVENT:
            if (isDescriptionEmpty(inputArr)) {
                throw new DukeException("Hey now.. The description of an event cannot be empty. >:(");
            }
            taskInput = inputArr[1];
            String[] eArr = taskInput.split("/");
            String eventDesc = eArr[0].trim();
            String from = eArr[1].trim().substring(5);
            String to = eArr[2].trim().substring(3);
            Event event = new Event(eventDesc, from, to);
            return new AddCommand(CommandType.EVENT, event);
        case FIND:
            return new FindCommand(inputArr[1]);
        case BYE:
            return new ByeCommand();
        default:
            throw new DukeException("Huh? What do you mean? :o");
        }
    }
}
