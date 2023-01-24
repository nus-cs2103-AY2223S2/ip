package duke.task;

import java.time.LocalDate;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String des) {
        this.description = des;
        this.isDone = false;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "[X] " + this.getDescription() : "[] " + this.getDescription();
    }

    public String getDescription() {
        return this.description;
    }

    public String encode() {
        return String.format("%s ### %s", this.isDone, this.description);
    }

    public boolean fallsOnDate(LocalDate date) {
        return false;
    }
}