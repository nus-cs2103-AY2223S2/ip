package duke;


/**
 * Parent class for all the tasks
 */
public class Task {
    private boolean mark;
    private String value;

    public Task(String value, boolean mark) {
        this.value = value;
        this.mark = mark;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toFile() {
        return null;
    }
    public String toString() {
        return "[" + (isMark() ? "X" : " ") + "] " + getValue()  ;
    }
}
