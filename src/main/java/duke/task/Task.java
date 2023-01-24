package duke.task;

import java.io.Serializable;
import java.util.Objects;

public abstract class Task implements Serializable {

    private static final long serialVersionUID = 4852600493024294334L;

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        if (isDone) {
            throw new IllegalStateException("Task is already marked as done");
        }
        isDone = true;
    }

    public void unmarkAsDone() {
        if (!isDone) {
            throw new IllegalStateException("Task is already marked as not done");
        }
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        return Objects.equals(description, task.description) && isDone == task.isDone;
    }
}
