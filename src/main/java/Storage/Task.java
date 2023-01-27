package storage;

import leoException.IncorrectMarkException;
import leoException.IncorrectUnmarkException;
import ui.Ui;

public class Task {

    private final String task;
    private boolean done;
    private TaskType type;

    public Task(String task) {
        this.task = task;
        this.done = false;
        this.type = TaskType.TODO;
    }

    public String getTask() {
        return this.task;
    }

    public boolean isDone() {
        return this.done;
    }

    public void mark() throws IncorrectMarkException {
        if (this.done) {
            throw new IncorrectMarkException("This task was already marked previously.");
        }
        this.done = true;
    }

    public void unmark() throws IncorrectUnmarkException {
        if (!this.done) {
            throw new IncorrectUnmarkException("This task has not been marked.");
        }
        this.done = false;
    }

    public String saveFormat() {
        return task;
    }

    public TaskType getType() {
        return this.type;
    }

    public void setType(TaskType tt) {
        this.type = tt;
    }

    public String typeAndStatus() {
        return Ui.type(this) + Ui.completion(this);
    }

}
