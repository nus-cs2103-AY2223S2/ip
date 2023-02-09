package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

import duke.command.*;
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
        sort,
        bye
    }

    private enum TaskType {
        T,
        D,
        E
    }

    private enum MinimumLengths {
        EMPTY_USER_INPUT(0),
        USER_INPUT_TODO(2),
        USER_INPUT_DEADLINE(5),
        USER_INPUT_EVENT(8),
        USER_INPUT_MARK(2),
        USER_INPUT_UNMARK(2),
        USER_INPUT_DELETE(2),
        USER_INPUT_SORT(2),
        USER_INPUT_FIND(2),
        FILE_INPUT_TODO(3),
        FILE_INPUT_DEADLINE(4),
        FILE_INPUT_EVENT(5);

        public final int length;

        MinimumLengths(int length) {
            this.length = length;
        }
    }

    public static String[] getIndexes(String[] splitInputs) {
        return Arrays.stream(splitInputs, 1, splitInputs.length).toArray(String[]::new);
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
        checkUserInputFormat(splitInputs.length, MinimumLengths.EMPTY_USER_INPUT.length,
                "Sorry, Fake Duke wants you to enter something.");
        Command c;

        Action action;
        try {
            action = Action.valueOf(splitInputs[0]);
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but Fake Duke doesn't know what that means :-(");
        }

        switch (action) {
        case todo:
            checkUserInputFormat(splitInputs.length, MinimumLengths.USER_INPUT_TODO.length,
                    "The description of a todo cannot be empty.");
            Todo todo = new Todo(input.split(" ", 2)[1]);
            c = new AddCommand(todo);
            break;
        case deadline:
            checkUserInputFormat(splitInputs.length, MinimumLengths.USER_INPUT_DEADLINE.length,
                    "Deadline must follow this format: deadline {description} /by {YYYY-MM-DD} {HH:MM}");
            Deadline deadline = new Deadline(input.split(" ", 2)[1]);
            c = new AddCommand(deadline);
            break;
        case event:
            checkUserInputFormat(splitInputs.length, MinimumLengths.USER_INPUT_EVENT.length,
                    "Event must follow this format: event {description} /from {YYYY-MM-DD} {HH:MM} "
                    + "/to {YYYY-MM-DD} {HH:MM}");
            Event event = new Event(input.split(" ", 2)[1]);
            c = new AddCommand(event);
            break;
        case list:
            c = new ListCommand();
            break;
        case mark:
            checkUserInputFormat(splitInputs.length, MinimumLengths.USER_INPUT_MARK.length,
                    "The task index cannot be empty.");
            String[] markIndexes = getIndexes(splitInputs);
            c = new MarkCommand(markIndexes);
            break;
        case unmark:
            checkUserInputFormat(splitInputs.length, MinimumLengths.USER_INPUT_UNMARK.length,
                    "The task index cannot be empty.");
            String[] unmarkIndexes = getIndexes(splitInputs);
            c = new UnmarkCommand(unmarkIndexes);
            break;
        case delete:
            checkUserInputFormat(splitInputs.length, MinimumLengths.USER_INPUT_DELETE.length,
                    "The task index cannot be empty.");
            c = new DeleteCommand(getIndexes(splitInputs));
            break;
        case bye:
            c = new ExitCommand();
            break;
        case find:
            checkUserInputFormat(splitInputs.length, MinimumLengths.USER_INPUT_FIND.length,
                    "You must include the keyword you wish to search.");
            c = new FindCommand(input.split(" ", 2)[1]);
            break;
        case sort:
            checkUserInputFormat(splitInputs.length, MinimumLengths.USER_INPUT_SORT.length,
                    "You must include the type of task you wish to search.");
            if (splitInputs.length > MinimumLengths.USER_INPUT_SORT.length) {
                throw new DukeException("You can only sort 1 type of task once.");
            }
            String taskType = input.split(" ", 2)[1].toLowerCase();
            if (!taskType.equals("d") && !taskType.equals("e")) {
                throw new DukeException("You can only sort deadlines or events.\n"
                        + "Valid input format: sort {d/e}");
            }
            c = new SortCommand(taskType);
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
    public static Task processTask(String input) throws DukeException {
        String[] splitInputs = input.split(" ~ ");

        TaskType taskType;
        try {
            taskType = TaskType.valueOf(splitInputs[0]);
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but Fake Duke doesn't know what that means :-(");
        }

        switch (taskType) {
        case T:
            checkFileInputFormat(splitInputs.length, MinimumLengths.FILE_INPUT_TODO.length,
                    "Todo task is of invalid format in the file.");
            return new Todo(splitInputs[2], splitInputs[1]);
        case D:
            checkFileInputFormat(splitInputs.length, MinimumLengths.FILE_INPUT_DEADLINE.length,
                    "Deadline task is of invalid format in the file.");
            return new Deadline(String.format("%s /by %s", splitInputs[2], splitInputs[3]), splitInputs[1]);
        case E:
            checkFileInputFormat(splitInputs.length, MinimumLengths.FILE_INPUT_EVENT.length,
                    "Event task is of invalid format in the file.");
            return new Event(String.format("%s /from %s /to %s", splitInputs[2], splitInputs[3],
                    splitInputs[4]), splitInputs[1]);
        default:
            return null;
        }
    }

    /**
     * Checks the format of the input entered by user.
     *
     * @param inputLength Length of the input entered by the user.
     * @param minimumLength Valid minimum length of the input.
     * @param errorMessage Error message to be printed on the program.
     * @throws DukeException Throws exception if input length does not meet minimum length.
     */
    public static void checkUserInputFormat(int inputLength, int minimumLength, String errorMessage)
            throws DukeException {
        boolean isInvalidLength = inputLength < minimumLength;
        if (isInvalidLength) {
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Checks the format of the input in the local file.
     *
     * @param inputLength Length of the task in the local file.
     * @param minimumLength Valid minimum length of the input.
     * @param errorMessage Error message to be printed on the program.
     */
    public static void checkFileInputFormat(int inputLength, int minimumLength, String errorMessage) {
        boolean isValidLength = inputLength >= minimumLength;
        assert isValidLength : String.format("Local file data/tasks.txt does not have valid format. %s",
                errorMessage);
    }

    /**
     * Returns the valid format of the datetime of the Task.
     * Valid format: {yyyy-MM-dd HH:mm}
     *
     * @param input Datetime input provided by user.
     * @return Datetime of Task in valid format.
     */
    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, formatter);
    }
}
