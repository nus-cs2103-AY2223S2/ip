package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Makes sense of the user command entered into Duke.
 */
public class Parser {
    private enum Action {
        todo,
        deadline,
        event,
        list,
        mark,
        unmark,
        delete,
        find,
        bye
    }

    private enum TaskType {
        T,
        D,
        E
    }

    /**
     * Parses input data entered by user.
     *
     * @param input User input for the program menu.
     * @return Command that user entered.
     * @throws DukeException Throws exception if input format is invalid.
     */
    public static Command parse(String input) throws DukeException {
        String[] splitInputs = input.split(" ");
        checkInputFormat(splitInputs.length, 0,
                "Sorry, Fake Duke wants you to enter something.");
        Command c;

        Action action;
        try {
            action = Action.valueOf(splitInputs[0]);
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }

        switch (action) {
        case todo:
            checkInputFormat(splitInputs.length, 2,
                    "The description of a todo cannot be empty.");
            Todo todo = new Todo(input.split(" ", 2)[1]);
            c = new AddCommand(todo);
            break;
        case deadline:
            checkInputFormat(splitInputs.length, 5,
                    "Deadline must follow this format: deadline {description} /by {YYYY-MM-DD} {HH:MM}");
            Deadline deadline = new Deadline(input.split(" ", 2)[1]);
            c = new AddCommand(deadline);
            break;
        case event:
            checkInputFormat(splitInputs.length, 8,
                    "Event must follow this format: event {description} /from {YYYY-MM-DD} {HH:MM} "
                    + "/to {YYYY-MM-DD} {HH:MM}");
            Event event = new Event(input.split(" ", 2)[1]);
            c = new AddCommand(event);
            break;
        case list:
            c = new ListCommand();
            break;
        case mark:
            checkInputFormat(splitInputs.length, 2,
                    "The task index cannot be empty.");
            int markIndex = Integer.parseInt(splitInputs[1]);
            c = new MarkCommand(markIndex);
            break;
        case unmark:
            checkInputFormat(splitInputs.length, 2,
                    "The task index cannot be empty.");
            int unmarkIndex = Integer.parseInt(splitInputs[1]);
            c = new UnmarkCommand(unmarkIndex);
            break;
        case delete:
            checkInputFormat(splitInputs.length, 2, "The task index cannot be empty.");
            c = new DeleteCommand(Integer.parseInt(splitInputs[1]));
            break;
        case bye:
            c = new ExitCommand();
            break;
        case find:
            checkInputFormat(splitInputs.length, 2,
                    "You must include the keyword you wish to search.");
            c = new FindCommand(input.split(" ", 2)[1]);
            break;
        default:
            c = new Command();
        }

        return c;
    }

    /**
     * Processes one task from the list of tasks in file in the local storage.
     *
     * @param input One task to be processed.
     * @throws DukeException Throws exception if input format is invalid.
     */
    public Task processTask(String input) throws DukeException {
        String[] splitInputs = input.split(" ~ ");

        TaskType taskType;
        try {
            taskType = TaskType.valueOf(splitInputs[0]);
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }

        switch (taskType) {
        case T:
            checkInputFormat(splitInputs.length, 3,
                    "Todo task is of invalid format in the file.");
            return new Todo(splitInputs[2], splitInputs[1]);
        case D:
            checkInputFormat(splitInputs.length, 4,
                    "Deadline task is of invalid format in the file.");
            return new Deadline(String.format("%s /by %s", splitInputs[2], splitInputs[3]), splitInputs[1]);
        case E:
            checkInputFormat(splitInputs.length, 5,
                    "Event task is of invalid format in the file.");
            return new Event(String.format("%s /from %s /to %s", splitInputs[2], splitInputs[3],
                    splitInputs[4]), splitInputs[1]);
        default:
            return null;
        }
    }

    /**
     * Checks the format of the input entered by user or task in the local file.
     *
     * @param inputLength Length of the input entered by the user or task in the local file.
     * @param minimumLength Valid minimum length of the input.
     * @param errorMessage Error message to be printed on the program.
     * @throws DukeException Throws exception if input length does not meet minimum length.
     */
    public static void checkInputFormat(int inputLength, int minimumLength, String errorMessage) throws DukeException {
        if (inputLength < minimumLength) {
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Returns the valid format of the datetime of the Task.
     * Valid format: {yyyy-MM-dd HH:mm}
     *
     * @param input Datetime input provided by user.
     * @return Datetime of Task in valid format.
     */
    public LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, formatter);
    }
}
