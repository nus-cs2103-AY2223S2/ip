package Storage;

import LeoException.IncorrectMarkException;
import LeoException.IncorrectUnmarkException;

public class Task {
    protected String task;
    protected boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return this.task;
    }

    public String status() {
        return (done ? "[X] " : "[ ] ");
    }

    public void mark() throws IncorrectMarkException {
        if (this.done) {
            throw new IncorrectMarkException("Leo: This task was already marked previously.");
        }
        this.done = true;
    }

    public void unmark() throws IncorrectUnmarkException {
        if (!this.done) {
            throw new IncorrectUnmarkException("Leo: This task has not been marked.");
        }
        this.done = false;
    }
}
