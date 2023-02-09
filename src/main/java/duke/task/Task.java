package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a parent class for the different categories of tasks
 */
public abstract class Task {
    private String content;
    boolean done = false;

    Task(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        String mark = this.getDoneStatus() ? "X" : " ";
        return String.format("[%s] %s", mark, this.content);
    }

    public boolean getDoneStatus() {
        return this.done;
    }

    public void setDoneStatus(boolean status) {
        this.done = status;
    }

    public String getContent() {
        return this.content;
    }

    public abstract LocalDateTime getStartDate();
}
