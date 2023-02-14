package connor.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

import connor.task.Deadline;
import connor.task.Event;
import connor.task.InvalidTaskException;
import connor.task.Task;
import connor.task.TaskList;
import connor.task.Todo;
import connor.ui.Ui;

/**
 * Parser object that parses information from input and memory.
 */
public class Parser {

    /**
     * Throws InvalidTaskException if an invalid input such as "" or " ".
     *
     * @param input String that comes after command.
     * @throws InvalidTaskException if input is only blanks spaces.
     */
    private void validateName(String input) throws InvalidTaskException {
        if (input.trim().length() < 1) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Returns a String in a parseable format into LocalDateTime.
     *
     * @param input substring from user input containing date and time.
     * @return String that is parseable into LocalDateTime.
     */
    public String dateTimeFormat(String input) {
        String[] dateTimePair = input.split(" ");
        String date = dateTimePair[0];
        String time = dateTimePair[1];
        String hrStr = time.substring(0, 2);
        String minStr = time.substring(2, 4);
        return date + "T" + hrStr + ":" + minStr + ":00";
    }

    /**
     * Returns a LocalDateTime object that has the date and time of the input.
     *
     * @param input substring from user input containing date and time.
     * @return LocalDateTime with the date and time of the input.
     * @throws DateTimeException when the input is an invalid format that cannot be parsed.
     */
    public LocalDateTime parseDateTime(String input) throws DateTimeException {
        String formattedDateTime = dateTimeFormat(input);
        return LocalDateTime.parse(formattedDateTime);
    }

    /**
     * Returns a String array of size 2, intended to be used when parsing Deadline tasks.
     * Index 0 is the taskName and index 1 is the Deadline.
     *
     * @param input String that comes after command.
     * @return String array of only taskName and Deadline.
     * @throws InvalidTaskException if taskName is blank spaces.
     */
    private String[] getNameDeadlinePair(String input) throws InvalidTaskException {
        int byIndex = input.indexOf("/by");
        if (byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] pair = new String[2];
        pair[0] = input.substring(0, byIndex - 1);
        validateName(pair[0]);
        pair[1] = input.substring(byIndex + 4);

        return pair;
    }

    /**
     * Returns a String array of size 3, intended to be used when parsing Event tasks.
     * Index 0 is the taskName, index 1 is start period and index 2 is end period.
     *
     * @param input String that comes after command.
     * @return String array of only taskName, start period and end period.
     * @throws InvalidTaskException if taskName is blank spaces.
     */
    private String[] getNameStartEndTuple(String input) throws InvalidTaskException {
        int fromIndex = input.indexOf("/from");
        int byIndex = input.indexOf("/to");
        if (fromIndex < 1 || byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] tuple = new String[3];
        tuple[0] = input.substring(0, fromIndex - 1);
        validateName(tuple[0]);
        tuple[1] = input.substring(fromIndex + 6, byIndex - 1);
        tuple[2] = input.substring(byIndex + 4);
        return tuple;
    }

    /**
     * Returns a Task instance depending on the command.
     * Information refers to the input of the user after the command.
     * Throws InvalidTaskException if taskName is invalid.
     *
     * @param command Command of the input.
     * @param information String that comes after command.
     * @return Task instance.
     * @throws InvalidTaskException if taskName is blank spaces.
     */
    public Task parseCommand(String command, String information) throws InvalidTaskException {
        try {
            switch (Commands.valueOf(command).toString()) {
            case "TODO":
                validateName(information);
                return new Todo(information);

            case "DEADLINE":
                String[] pair = getNameDeadlinePair(information);
                LocalDateTime deadline = parseDateTime(pair[1]);
                return new Deadline(pair[0], deadline);

            case "EVENT":
                String[] tuple = getNameStartEndTuple(information);
                LocalDateTime start = parseDateTime(tuple[1]);
                LocalDateTime end = parseDateTime(tuple[2]);
                return new Event(tuple[0], start, end);

            default:
                throw new InvalidTaskException();
            }
        } catch (DateTimeException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Returns the String that comes after the command.
     * This should only be called for inputs with more than 1 word.
     *
     * @param input Full user input String.
     * @return String that comes after the command.
     * @throws InvalidTaskException if there is no String after the command
     */
    private String getTask(String input) throws InvalidTaskException {
        if (input.indexOf(' ') == -1) {
            throw new InvalidTaskException();
        }
        return input.substring(input.indexOf(' ') + 1).trim();
    }

    /**
     * Returns the command of the input.
     *
     * @param input the first word of the user input String.
     * @return String of the input command.
     */
    private String getCommand(String input) {
        if (input.indexOf(' ') == -1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, input.indexOf(' ')).toUpperCase();
        }
    }

    /**
     * Returns a String response if the input command is valid.
     * Else, print the corresponding error message.
     *
     * @param input Full user input String.
     * @param tasks current collection of Tasks.
     * @param ui UI to print messages.
     * @return String response in regard to user input.
     */
    public String parse(String input, TaskList tasks, Ui ui) {
        String command = getCommand(input).trim();
        try {
            switch (Commands.valueOf(command)) {
            case HI:
                return ui.greetings("HI");

            case BYE:
                return ui.greetings("BYE");

            case LIST:
                return tasks.toString();

            case MARK:
                return tasks.markDone(Integer.parseInt(getTask(input)), ui);

            case UNMARK:
                return tasks.markUndone(Integer.parseInt(getTask(input)), ui);

            case TODO:
            case DEADLINE:
            case EVENT:
                Task task = parseCommand(command, getTask(input));
                tasks.addTask(task);
                return ui.addTaskMessage(task, tasks.getSize());

            case DELETE:
                return tasks.deleteTask(getTask(input), ui);

            case DELETEALL:
                tasks.deleteAllTask();
                return ui.deleteAllMessage();

            case FIND:
                return tasks.find(getTask(input));

            case SORT:
                return tasks.sort(ui);
            default:
                return "Invalid command";
            }
        } catch (IllegalArgumentException e) {
            return "Invalid command";
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }
}
