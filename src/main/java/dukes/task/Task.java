package dukes.task;

import java.time.LocalDate;

/**
 * Parent class that wraps the information of each Task into object.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;
    /** The type of the Task it belongs to. "T" for ToDo, "D" for DeadLine, "E" for Event. */
    protected String tag;

    /**
     * Constructor of Task class.
     *
     * @param taskName name (main content) of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Constructor of Task class.
     *
     * @param taskName name (main content) of the task.
     * @param isDone specifies if the task has been done or not.
     */
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

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    String getFromTime() {
        return "";
    }

    String getToTime() {
        return "";
    }

    /** Dummy method to be used by subclass. Returns dummy date. */
    public LocalDate getDeadLine() {
        return LocalDate.parse("1970-01-01");
    }

    public void setDeadLine(LocalDate newDeadLine) {};

    /** Dummy method to be used by subclass. Returns dummy date. */
    public LocalDate getStart() {
        return LocalDate.parse("1970-01-01");
    }

    public void setStart(LocalDate newStart) {};

    /** Dummy method to be used by subclass. Returns dummy date. */
    public LocalDate getEnd() {
        return LocalDate.parse("1970-01-01");
    }

    public void setEnd(LocalDate newEnd) {};

    public String getTag() { return this.tag; }

    /**
     * Returns a string containing {if the task is completed} +
     * the content of the task.
     *
     * @return a string describing if the task is completed and its content.
     */
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
