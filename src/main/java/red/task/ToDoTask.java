package red.task;

/**
 * This class is for tasks that do not have a timeframe associated with it.
 */
public class ToDoTask extends Task{

    /**
     * The constructor of the ToDoTask that takes in description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
