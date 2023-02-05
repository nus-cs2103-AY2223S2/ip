package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;


/**
 * Represents an input Parser.
 * A Parser makes sense of User input and decides Duke's follow-up action.
 */
public class Parser {

    private static final String[] VALUE_COMMANDS = {"unmark ", "mark ", "delete ", "find "};
    private static final String[] TASK_COMMANDS = {"deadline ", "todo ", "event "};


    /**
     * Returns a Command object after parsing User input.
     *
     * @param fullCommand String representation of User input.
     * @return Command object that determines follow-up action.
     * @throws DukeException If there are discrepancies in User input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        }

        Command valueCommand = getValueCommand(fullCommand);
        if (valueCommand != null) {
            return valueCommand;
        }

        validateTaskCommand(fullCommand, TASK_COMMANDS);

        return getTaskCommand(fullCommand);
    }

    public static Command getTaskCommand(String fullCommand) throws DukeException {
        try {
            String[] inputArr = fullCommand.split(" ", 2);
            String taskType = inputArr[0];

            Task task = null;
            String[] newInputArr;
            switch (taskType) {
            case "todo":
                task = new ToDo(inputArr[1]);
                break;
            case "deadline":
                newInputArr = inputArr[1].split(" /by ", 2);
                task = new Deadline(newInputArr[0], newInputArr[1]);
                break;
            case "event":
                newInputArr = inputArr[1].split(" /from ", 2);
                String[] newerInputArr = newInputArr[1].split(" /to ", 2);
                task = new Event(newInputArr[0], newerInputArr[0], newerInputArr[1]);
                break;
            default:
                break;
            }
            return new AddCommand(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Error encountered when creating task! Please ensure input is valid.");
        }
    }
    public static Command getValueCommand(String fullCommand) {
        String command = Parser.returnCommand(fullCommand, VALUE_COMMANDS);
        String[] inputArr;
        Command valueCommand;
        switch (command) {
        case "unmark ":
            inputArr = fullCommand.split(" ");
            int toUnmark = Integer.parseInt(inputArr[1]);
            valueCommand = new UnmarkCommand(toUnmark);
            break;
        case "mark ":
            inputArr = fullCommand.split(" ");
            int toMark = Integer.parseInt(inputArr[1]);
            valueCommand = new MarkCommand(toMark);
            break;
        case "delete ":
            inputArr = fullCommand.split(" ");
            int toDelete = Integer.parseInt(inputArr[1]);
            valueCommand = new DeleteCommand(toDelete);
            break;
        case "find ":
            inputArr = fullCommand.split(" ", 2);
            String[] toFind = inputArr[1].split(" ");
            valueCommand = new FindCommand(toFind);
            break;
        default:
            return null;
        }
        return valueCommand;
    }

    /**
     * Returns the corresponding command in User input.
     *
     * @param input String representation of User input.
     * @param commands Array of valid commands to find within User input.
     * @return String representation of just the command.
     */
    public static String returnCommand(String input, String[] commands) {
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            boolean gotMatch = match.find();

            if (gotMatch && match.start() == 0) {
                return s;
            }
        }
        return input;
    }

    /**
     * Validates the correctness of User add task command.
     *
     * @param input String representation of User add task command.
     * @param commands Array of valid add task commands.
     * @throws DukeException If User input is invalid. (e.g. Add deadline task without supplying end time.)
     */
    public static void validateTaskCommand(String input, String[] commands) throws DukeException {
        input = input.trim();
        for (String s : commands) {
            if (s.equals(input + " ")) {
                throw new DukeException("OOPS!!! The description of a " + input + " task cannot be empty!");
            }
        }

        boolean isCorrect = false;
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            boolean gotMatch = match.find() && (match.start() == 0);
            isCorrect = isCorrect || gotMatch;
        }

        if (!isCorrect) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means! :(");
        }
    }
}
