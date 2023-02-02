package duke.task;

import duke.exception.DukeException;
import duke.exception.ERROR;

/**
 * An abstraction of all Tasks which contain a description and an isDone flag indicating its completion.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

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
            task = new Deadline(params[0], params[1]);
        } else if (s.startsWith("event")) {
            String desc = s.replace("event", "").trim();
            if (desc.isBlank()) {
                throw new DukeException(ERROR.EVENT_EMPTY.getMessage());
            }
            String[] params = desc.split("( /from | /to )");
            task = new Event(params[0], params[1], params[2]);
        }

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
    public static Task parseTaskFromDB(String s) throws DukeException {
        s = s.trim();
        Task task = null;

        if (s.charAt(1) == 'T') {
            String desc = s.substring(7);
            task = new Todo(desc);
        } else if (s.charAt(1) == 'D') {
            String desc = s.substring(7);
            String[] params = desc.split("( \\(by: |\\))");
            task = new Deadline(params[0], params[1]);
        } else if (s.charAt(1) == 'E') {
            String desc = s.substring(7);
            String[] params = desc.split("( \\(from: | to: |\\))");
            task = new Event(params[0], params[1], params[2]);
        } else {
            throw new DukeException(ERROR.CORRUPTED_TASK_DATA.getMessage());
        }

        if (s.charAt(4) == 'X') {
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
}
