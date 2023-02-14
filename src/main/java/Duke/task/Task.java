package Duke.task;

/**
 * Represents a general task class,
 * with string describing the content of the task,
 * and boolean mark representing weather the task
 * is done.
 */
public class Task {
    private String string;
    private Boolean isMark;

    /**
    * Constructor for Event task.
    */
    public Task(String string) {
        this.string = string;
        this.isMark = false;
    }

    public boolean isMark() {
        return isMark;
    }

    public void mark() {
        this.isMark = true;
    }

    public void unmark() {
        this.isMark = false;
    }

    public String taskString() {
        return "";
    }

    public String getString() {
        return string;
    }
}
