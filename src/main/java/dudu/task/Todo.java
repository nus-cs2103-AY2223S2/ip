package dudu.task;

/**
 * Task class for todo type
 */
public class Todo extends Task {
    private String name;

    /**
     * Constructor for todo task, default undone
     * @param name Name of the task
     */
    public Todo(String name) {
        super(name, false);
        this.name = name;
    }

    /**
     * Constructor for todo task
     * @param name Name of the task
     * @param isDone The completion status of the task
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
        this.name = name;
    }
    @Override
    public String getType() {
        return "Todo";
    }
    @Override
    public String getStatus() {
        return isDone() ? "1" : "0";
    }
    @Override
    public String getDescription() {
        return name;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
