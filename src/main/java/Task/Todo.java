package Task;

public class Todo extends Task {

    /**
     * Constructor to create new task
     *
     * @param task new task to be added
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String statusIcon = this.completed ? "X" : " ";
        return "[T][" + statusIcon + "] " + this.task;
    }
}
