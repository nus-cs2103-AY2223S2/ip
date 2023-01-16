package duke.task;

/**
 * Todo
 */
public class Todo extends Task {

    /**
     * @see Task#Task()
     */
    public Todo(String title) {
        this(title, false);
    }

    /**
     * @see Task#Task(String title, boolean isDone)
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * @see Task#toCsv()
     */
    public String toCsv() {
        return "T," + super.toCsv() + ",,";
    }

    /**
     * @see Task#toString()
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
