package duke.parser;

import duke.command.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;

/**
 * Represents an input Parser.
 * A Parser makes sense of User input and decides Duke's follow-up action.
 */
public class Parser {

    private static final String[] VALUE_COMMANDS = {"unmark ", "mark ", "delete "};
    private static final String[] TASK_COMMANDS = {"deadline ", "todo ", "event "};

    public Parser() {
    }

    /**
     * Returns a Command object after parsing User input.
     *
     * @param fullCommand String representation of User input.
     * @param tasks TaskList instance containing existing tasks.
     * @return Command object that determines follow-up action.
     * @throws DukeException If there are discrepancies in User input.
     */
    public static Command parse(String fullCommand, TaskList tasks) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        }

        String command = Parser.returnCommand(fullCommand, VALUE_COMMANDS);
        switch (command) {
            case "unmark ": {
                String[] inputArr = fullCommand.split(" ");
                int toUnmark = Integer.parseInt(inputArr[1]);
                return new UnmarkCommand(toUnmark);
            }

            case "mark ": {
                String[] inputArr = fullCommand.split(" ");
                int toMark = Integer.parseInt(inputArr[1]);
                return new MarkCommand(toMark);
            }

            case "delete ": {
                String[] inputArr = fullCommand.split(" ");
                int toDelete = Integer.parseInt(inputArr[1]);
                if (toDelete > tasks.getNumTasks()) {
                    throw new DukeException("Task does not exist! Please enter valid input!");
                }
                return new DeleteCommand(toDelete);
            }
        }

        validateTaskCommand(fullCommand, TASK_COMMANDS);

        String[] inputArr = fullCommand.split(" ", 2);
        String taskType = inputArr[0];

        Task task = null;
        switch (taskType) {
            case "todo": {
                task = new ToDo(inputArr[1]);
                break;
            }
            case "deadline": {
                String[] newInputArr = inputArr[1].split(" /by ", 2);
                task = new Deadline(newInputArr[0], newInputArr[1]);
                break;
            }
            case "event": {
                String[] newInputArr = inputArr[1].split(" /from ", 2);
                String[] newerInputArr = newInputArr[1].split(" /to ", 2);
                task = new Event(newInputArr[0], newerInputArr[0], newerInputArr[1]);
                break;
            }
        }
        return new AddCommand(task);
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
     * @throws DukeException If User input is invalid. (e.g. Add deadline task without supplying endtime.)
     */
    public static void validateTaskCommand(String input, String[] commands) throws DukeException {
        input = input.trim();
        for (String s : commands) {
            if (s.equals(input + " ")) {
                throw new DukeException("OOPS!!! The description of a " + input + " task cannot be empty!");
            }
        }

        boolean correct = false;
        for (String s : commands) {
            Pattern word = Pattern.compile(s);
            Matcher match = word.matcher(input);
            boolean gotMatch = match.find() && (match.start() == 0);
            correct = correct || gotMatch;
        }

        if (!correct) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means! :(");
        }
    }
}
