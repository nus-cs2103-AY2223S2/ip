import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {

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
        default:
            throw new DukeUnknownActionException();
        }
    }

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

        if (taskType.equals("T")) {
            String[] taskNameArray = Arrays.copyOfRange(tokens, 2, tokens.length);
            String taskName = String.join("|", taskNameArray);
            TodoTask todoTask = new TodoTask(taskName);

            if (isDone) {
                todoTask.markDone();
            }

            return todoTask;
        } else if (taskType.equals("D")) {

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
        } else if (taskType.equals("E")) {

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
        } else {
            throw new DukeInvalidFileFormatException();
        }
    }
}
