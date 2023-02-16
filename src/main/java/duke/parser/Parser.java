package duke.parser;

import java.util.InputMismatchException;
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
        Command singleCommand = getSingleCommand(fullCommand);
        if (singleCommand != null) {
            return singleCommand;
        }
        Command valueCommand = getValueCommand(fullCommand);
        if (valueCommand != null) {
            return valueCommand;
        }
        validateTaskCommand(fullCommand, TASK_COMMANDS);
        return getTaskCommand(fullCommand);
    }
    public static Command getSingleCommand(String fullCommand) {
        Command command;
        switch (fullCommand) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand(false);
            break;
        case "orderedlist":
            command = new ListCommand(true);
            break;
        default:
            command = null;
        }
        return command;
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
                if (!fullCommand.contains(" /by ")) {
                    throw new DukeException("Missing deadline for your deadline task!");
                }
                newInputArr = inputArr[1].split(" /by ", 2);
                task = new Deadline(newInputArr[0], newInputArr[1]);
                break;
            case "event":
                if (!fullCommand.contains(" /from ") || !fullCommand.contains(" /to ")) {
                    throw new DukeException("Missing Starting or Ending time for your Event!");
                }
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
    public static Command getValueCommand(String fullCommand) throws DukeException {
        String command = Parser.returnCommand(fullCommand, VALUE_COMMANDS);
        String[] inputArr;
        Command valueCommand;
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Missing task index! Please indicate the task ID.");
        } catch (NumberFormatException e) {
            throw new DukeException("Task ID must be an integer! Please try again.");
        }
    }

    /**
     * Returns the corresponding command in User input.
     *
     * @param input String representation of User input.
     * @param commands Array of valid commands to find within User input.
     * @return String representation of just the command.
     */
    public static String returnCommand(String input, String[] commands) {
        assert commands.length > 0 : "There are no commands available.";
        boolean isMatch;
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            isMatch = match.find();

            if (isMatch && match.start() == 0) {
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
        assert commands.length > 0 : "There are no commands available.";
        input = input.trim();
        for (String s : commands) {
            if (s.equals(input + " ")) {
                throw new DukeException("OOPS!!! The description of a " + input + " task cannot be empty!");
            }
        }

        boolean isCorrect = false;
        boolean isMatch;
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            isMatch = match.find() && (match.start() == 0);
            isCorrect = isMatch;
            if (isCorrect) {
                break;
            }
        }

        if (!isCorrect) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means! :(");
        }
    }
}
