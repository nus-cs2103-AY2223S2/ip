package duke.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DueOnCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeInvalidFileFormatException;
import duke.exceptions.DukeUnknownActionException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;

public class Parser {

    /**
     * Parses a user command.
     *
     * @param line A string representing a line of user input, excluding the newline character.
     * @return A {@code Command} object parsed from the line {@code line}.
     * @throws DukeUnknownActionException If the action in the user command is unknown.
     */
    public static Command parseUserCommand(String line) throws DukeUnknownActionException {
        String[] tokens = line.split(" ");
        Action action;

        try {
            action = Action.valueOf(tokens[0]);
        } catch (IllegalArgumentException e) {
            throw new DukeUnknownActionException();
        }

        switch (action) {
        case list:
            return new ListCommand();
        case mark:
            return new MarkCommand(tokens);
        case unmark:
            return new UnmarkCommand(tokens);
        case todo:
            return new TodoCommand(tokens);
        case deadline:
            return new DeadlineCommand(tokens);
        case event:
            return new EventCommand(tokens);
        case delete:
            return new DeleteCommand(tokens);
        case bye:
            return new ByeCommand();
        case dueon:
            return new DueOnCommand(tokens);
        case find:
            return new FindCommand(tokens);
        default:
            throw new DukeUnknownActionException();
        }
    }

    /**
     * Parses a line from the {@code duke.txt} file.
     *
     * @param line A string representing a line in the {@code duke.txt} file, excluding the newline
     *             character.
     * @return A {@code Task} object parsed from the line {@code line} in the {@code duke.txt} file.
     * @throws DukeInvalidFileFormatException If the {@code duke.txt} file is incorrectly formatted.
     */
    public static Task parseTask(String line) throws DukeInvalidFileFormatException {
        // need to escape the literal character "|" since it is a special character used
        // in regex
        String[] tokens = line.split("\\|");

        if (tokens.length < 3) {
            throw new DukeInvalidFileFormatException();
        }

        String taskType = tokens[0];
        String isDoneString = tokens[1];
        boolean isDone;

        if (isDoneString.equals("1")) {
            isDone = true;
        } else if (isDoneString.equals("0")) {
            isDone = false;
        } else {
            throw new DukeInvalidFileFormatException();
        }

        switch (taskType) {
        case "T":
            return parseTodoTask(tokens, isDone);
        case "D":
            return parseDeadlineTask(tokens, isDone);
        case "E":
            return parseEventTask(tokens, isDone);
        default:
            throw new DukeInvalidFileFormatException();
        }
    }

    private static Task parseTodoTask(String[] tokens, boolean isDone) {
        String[] taskNameArray = Arrays.copyOfRange(tokens, 2, tokens.length);
        String taskName = String.join("|", taskNameArray);
        TodoTask todoTask = new TodoTask(taskName);

        if (isDone) {
            todoTask.markDone();
        }

        return todoTask;
    }

    private static Task parseDeadlineTask(String[] tokens, boolean isDone) throws DukeInvalidFileFormatException {
        if (tokens.length < 4) {
            throw new DukeInvalidFileFormatException();
        }

        String[] taskNameArray = Arrays.copyOfRange(tokens, 2, tokens.length - 1);
        String taskName = String.join("|", taskNameArray);
        String by = tokens[tokens.length - 1];

        LocalDate byDate;

        try {
            byDate = LocalDate.parse(by, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidFileFormatException();
        }

        DeadlineTask deadlineTask = new DeadlineTask(taskName, byDate);

        if (isDone) {
            deadlineTask.markDone();
        }

        return deadlineTask;
    }

    private static Task parseEventTask(String[] tokens, boolean isDone) throws DukeInvalidFileFormatException {
        if (tokens.length < 5) {
            throw new DukeInvalidFileFormatException();
        }

        String[] taskNameArray = Arrays.copyOfRange(tokens, 2, tokens.length - 2);
        String taskName = String.join("|", taskNameArray);
        String from = tokens[tokens.length - 2];
        String to = tokens[tokens.length - 1];
        EventTask eventTask = new EventTask(taskName, from, to);

        if (isDone) {
            eventTask.markDone();
        }

        return eventTask;
    }
}
