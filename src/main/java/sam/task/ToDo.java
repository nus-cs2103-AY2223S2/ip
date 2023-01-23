package sam.task;

public class ToDo extends Task {
    public ToDo(String title) {
        this(title, false);
    }

    public ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    public String toSaveFormat() {
        return String.format(
                "T | %d | %s",
                getStatusNo(), title);
    }

    @Override
    public String toString() {
        return String.format(
                "[T][%c] %s",
                getStatusIcon(), title);
    }
}
