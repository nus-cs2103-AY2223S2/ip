package duke.task;

/**
 * Todo
 */
public class Todo extends Task {

    public Todo(String title) {
        this(title, false);
    }

    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    public String toCsv() {
        return "T," + super.toCsv() + ",,";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
