package duke.task;

import java.io.Serializable;

public class Task implements Serializable{
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public String getName() {
        return taskName;
    }

    public boolean isDone() {
        return done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean contains(String keyword) {
        return this.taskName.contains(keyword);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
