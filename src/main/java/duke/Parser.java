package duke;

import static commands.CommandType.DEADLINE;
import static commands.CommandType.EVENT;
import static commands.CommandType.TODO;

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

import java.time.LocalDate;

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
     * Parses the user's input and returns a command that is recognised and
     * executable by Sirius. It will also handle unknown commands by throwing a
     * DukeException
     *
     * @param input User's input from the scanner
     * @return A command that represents the input
     * @throws DukeException if the input command is not recognised
     */
    public static Command parse(String input) throws DukeException {
        Validator validator = new Validator();
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
            validator.validateDescription(inputArr, commandType.getValue());
            taskInput = inputArr[1];
            Todo todo = new Todo(taskInput);
            return new AddCommand(TODO, todo);
        case DEADLINE:
            validator.validateDescription(inputArr, commandType.getValue());
            taskInput = inputArr[1];
            Deadline deadline = generateDeadline(taskInput);
            return new AddCommand(DEADLINE, deadline);
        case EVENT:
            validator.validateDescription(inputArr, commandType.getValue());
            taskInput = inputArr[1];
            return new AddCommand(EVENT, generateEvent(taskInput));
        case FIND:
            return new FindCommand(inputArr[1]);
        case BYE:
            return new ByeCommand();
        default:
            throw new DukeException(generateDefaultMessage());
        }
    }

    private static String generateDefaultMessage() {
        return "Huh? What do you mean? :o";
    }

    private static Event generateEvent(String taskInput) {
        String[] eventInputArr = taskInput.split("/");
        String eventDesc = eventInputArr[0].trim();
        String from = eventInputArr[1].trim().substring(5);
        String to = eventInputArr[2].trim().substring(3);
        return new Event(eventDesc, from, to);
    }

    private static Deadline generateDeadline(String taskInput) throws DukeException {
        Validator validator = new Validator();
        String[] deadlineInputArr = taskInput.split("/by", 2);
        String deadlineDesc = deadlineInputArr[0].trim();
        String byStr = deadlineInputArr[1].trim();
        validator.validateDate(byStr);
        LocalDate byDate = LocalDate.parse(byStr);
        Deadline deadline = new Deadline(deadlineDesc, byDate);
        return deadline;
    }
}
