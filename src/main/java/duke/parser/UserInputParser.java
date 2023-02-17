package duke.parser;

import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Makes sense of user input into the app.
 */
public class UserInputParser {
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

    private enum MinimumLengths {
        EMPTY(0),
        TODO(2),
        DEADLINE(5),
        EVENT(8),
        MARK(2),
        UNMARK(2),
        DELETE(2),
        SORT(2),
        FIND(2);

        public final int length;

        MinimumLengths(int length) {
            this.length = length;
        }
    }

    /**
     * Parses input data entered by user.
     *
     * @param input for the program menu.
     * @return Command that user entered.
     * @throws DukeException if input format is invalid.
     */
    public static Command parse(String input) throws DukeException {
        String[] splitInputs = input.trim().split(" ");
        checkInputFormat(splitInputs.length, MinimumLengths.EMPTY.length,
                "Sorry, Fake Duke wants you to enter something.");
        return getCommand(getAction(splitInputs), input, splitInputs);
    }

    private static String[] getIndexes(String[] splitInputs) {
        return Arrays.stream(splitInputs, 1, splitInputs.length).toArray(String[]::new);
    }

    private static Action getAction(String[] splitInputs) throws DukeException {
        try {
            return Action.valueOf(splitInputs[0].toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but Fake Duke doesn't know what that means :-(");
        }
    }

    private static Command getCommand(Action action, String input, String[] splitInputs) throws DukeException {
        switch (action) {
        case todo:
            return getAddTodoCommand(input, splitInputs);
        case deadline:
            return getAddDeadlineCommand(input, splitInputs);
        case event:
            return getAddEventCommand(input, splitInputs);
        case list:
            return new ListCommand();
        case mark:
            return getMarkCommand(splitInputs);
        case unmark:
            return getUnmarkCommand(splitInputs);
        case delete:
            return getDeleteCommand(splitInputs);
        case bye:
            return new ExitCommand();
        case find:
            return getFindCommand(input, splitInputs);
        case sort:
            return getSortCommand(input, splitInputs);
        default:
            return new Command();
        }
    }

    private static Command getAddTodoCommand(String input, String[] splitInputs) throws DukeException {
        checkInputFormat(splitInputs.length, MinimumLengths.TODO.length,
                "The description of a todo cannot be empty.\nValid input format: todo {description}");
        Todo todo = new Todo(input.split(" ", 2)[1]);
        return new AddCommand(todo);
    }

    private static Command getAddDeadlineCommand(String input, String[] splitInputs) throws DukeException {
        checkInputFormat(splitInputs.length, MinimumLengths.DEADLINE.length,
                "Wrong deadline format.\n"
                        + "Valid input format: deadline {description} /by {YYYY-MM-DD} {HH:MM}");
        Deadline deadline = new Deadline(input.split(" ", 2)[1]);
        return new AddCommand(deadline);
    }

    private static Command getAddEventCommand(String input, String[] splitInputs) throws DukeException {
        checkInputFormat(splitInputs.length, MinimumLengths.EVENT.length,
                "Wrong event format."
                        + "\nValid input format: event {description} /from {YYYY-MM-DD} {HH:MM} "
                        + "/to {YYYY-MM-DD} {HH:MM}");
        Event event = new Event(input.split(" ", 2)[1]);
        return new AddCommand(event);
    }

    private static Command getMarkCommand(String[] splitInputs) throws DukeException {
        checkInputFormat(splitInputs.length, MinimumLengths.MARK.length,
                "The task index cannot be empty.\nValid input format: mark {index}");
        String[] markIndexes = getIndexes(splitInputs);
        return new MarkCommand(markIndexes);
    }

    private static Command getUnmarkCommand(String[] splitInputs) throws DukeException {
        checkInputFormat(splitInputs.length, MinimumLengths.UNMARK.length,
                "The task index cannot be empty.\nValid input format: unmark {index}");
        String[] unmarkIndexes = getIndexes(splitInputs);
        return new UnmarkCommand(unmarkIndexes);
    }

    private static Command getDeleteCommand(String[] splitInputs) throws DukeException {
        checkInputFormat(splitInputs.length, MinimumLengths.DELETE.length,
                "The task index cannot be empty.\nValid input format: delete {index}");
        return new DeleteCommand(getIndexes(splitInputs));
    }

    private static Command getFindCommand(String input, String[] splitInputs) throws DukeException {
        checkInputFormat(splitInputs.length, MinimumLengths.FIND.length,
                "You must include the keyword you wish to search.\nValid input format: find {keyword}");
        return new FindCommand(input.split(" ", 2)[1]);
    }

    private static Command getSortCommand(String input, String[] splitInputs) throws DukeException {
        checkInputFormat(splitInputs.length, MinimumLengths.SORT.length,
                "You must include the type of task you wish to search.");
        if (splitInputs.length > MinimumLengths.SORT.length) {
            throw new DukeException("You can only sort 1 type of task once.");
        }
        String taskType = input.split(" ", 2)[1].toLowerCase();
        if (!taskType.equals("d") && !taskType.equals("e")) {
            throw new DukeException("You can only sort deadlines or events.\n"
                    + "Valid input format: sort {d/e}");
        }
        return new SortCommand(taskType);
    }

    /**
     * Checks the format of the input entered by user.
     *
     * @param inputLength entered by the user.
     * @param minimumLength of the input.
     * @param errorMessage to be printed on the program.
     * @throws DukeException if input length does not meet minimum length.
     */
    private static void checkInputFormat(int inputLength, int minimumLength, String errorMessage)
            throws DukeException {
        boolean isInvalidLength = (inputLength < minimumLength);
        if (isInvalidLength) {
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Checks if input has invalid character "~".
     *
     * @return Command that was read.
     * @throws DukeException if command has invalid character.
     */
    public static String checkInputForInvalidCharacter(String command) throws DukeException {
        if (command.contains("~")) {
            throw new DukeException("Fake Duke doesn't like it when the input has the symbol ~.");
        }
        return command;
    }
}
