package duke.task;

import duke.exception.DukeException;
import duke.exception.ERROR;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * An abstraction of all Tasks which contain a description and an isDone flag indicating its completion.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    public static final List<DateTimeFormatter> DATETIME_FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy MM dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    public static final List<DateTimeFormatter> DATE_FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("MMM dd yyyy"),
            DateTimeFormatter.ofPattern("dd MMM yyyy"),
            DateTimeFormatter.ofPattern("yyyy MM dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    /**
     * Creates a new Task with a description.
     *
     * @param description Describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Parses a line which came from user input and creates the corresponding Task.
     *
     * @param s User input line describing the task.
     * @return The task with a type and parameters defined by the user's input.
     * @throws DukeException
     */
    public static Task parseTaskFromInput(String s) throws DukeException {
        s = s.trim();
        Task task = null;

        if (s.startsWith("todo")) {
            String desc = s.replace("todo", "").trim();
            if (desc.isBlank()) {
                throw new DukeException(ERROR.TODO_EMPTY.getMessage());
            }
            task = new Todo(desc);
        } else if (s.startsWith("deadline"))  {
            String desc = s.replace("deadline", "").trim();
            if (desc.isBlank()) {
                throw new DukeException(ERROR.DEADLINE_EMPTY.getMessage());
            }
            String[] params = desc.split(" /by ");
            if (params.length != 2) {
                throw new DukeException(ERROR.DEADLINE_WRONG_FORMAT.getMessage());
            }
            task = new Deadline(params[0], params[1]);
        } else if (s.startsWith("event")) {
            String desc = s.replace("event", "").trim();
            if (desc.isBlank()) {
                throw new DukeException(ERROR.EVENT_EMPTY.getMessage());
            }
            if (!desc.contains("/from") || !desc.contains("/to")) {
                throw new DukeException(ERROR.EVENT_WRONG_FORMAT.getMessage());
            }
            String[] params = desc.split("( /from | /to )");
            if (params.length != 3) {
                throw new DukeException(ERROR.EVENT_WRONG_FORMAT.getMessage());
            }
            task = new Event(params[0], params[1], params[2]);
        }

        assert task != null;

        return task;
    }

    /**
     * Parses a line which came from the database and creates the corresponding Task.
     * It is effectively a reverse of Task.toString().
     *
     * @param s Input line from database describing the task.
     * @return The task with a type and parameters defined by the input line.
     * @throws DukeException
     */
    public static Task parseTaskFromText(String s) throws DukeException {
        s = s.trim();
        Task task;

        assert s.length() >= 8;
        assert s.charAt(4) == 'X' || s.charAt(4) == ' ';

        if (s.charAt(1) == 'T') {
            String desc = s.substring(7);
            task = new Todo(desc);
        } else if (s.charAt(1) == 'D') {
            String desc = s.substring(7);
            String[] params = desc.split("( \\(by: |\\))");
            assert params.length == 2;
            task = new Deadline(params[0], params[1]);
        } else if (s.charAt(1) == 'E') {
            String desc = s.substring(7);
            String[] params = desc.split("( \\(from: | to: |\\))");
            assert params.length == 3;
            task = new Event(params[0], params[1], params[2]);
        } else {
            throw new DukeException(ERROR.CORRUPTED_TASK_DATA.getMessage());
        }

        if (s.charAt(4) == 'X') {
            task.markAsDone();
        }

        return task;
    }

    public static Task parseTaskFromCsv(String s) throws DukeException {
        s = s.trim();
        Task task;

        String[] params = s.split(",");
        assert params.length >= 3;

        switch (params[0]) {
            case "T":
                task = new Todo(params[2]);
                break;
            case "D":
                task = new Deadline(params[2], params[3]);
                break;
            case "E":
                task = new Event(params[2], params[3], params[4]);
                break;
            default:
                throw new DukeException(ERROR.CORRUPTED_TASK_DATA.getMessage());
        }

        if (params[1].equals("X")) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns an 'X' if the Task is done, else a space ' '.
     *
     * @return 'X' if the Task is done, else a space ' '.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns a String representation of this Task.
     *
     * @return String representation of this Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String toCsvString() {
        return String.format("%s,%s", this.getStatusIcon(), this.description);
    }
}
