package duke.tasks;

import duke.Duke;
import duke.exceptions.DukeException;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeadline() { return false; }

    public LocalDate getDeadline() throws DukeException {
        throw new DukeException("Not a deadline");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
