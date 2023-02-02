package duke.task;

import duke.exception.DukeException;
import duke.exception.ERROR;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
