package dukes.task;

import java.time.LocalDate;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String tag;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.tag = "";
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.tag = "";
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUnDone() {
        this.isDone = false;
    }

    public boolean hasDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    String getFromTime() {
        return "";
    }

    String getToTime() {
        return "";
    }

    public LocalDate getDeadLine() { // dummy deadline
        return LocalDate.parse("1970-01-01");
    }

    public LocalDate getStart() {
        return LocalDate.parse("1970-01-01");
    }

    public LocalDate getEnd() {
        return LocalDate.parse("1970-01-01");
    }

    public String getTag() { return this.tag; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.isDone) {
            sb.append("X");
        } else {
            sb.append(" ");
        }
        sb.append("] ").append(this.taskName);
        return sb.toString();
    }
}
