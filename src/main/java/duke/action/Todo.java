package duke.action;
import duke.data.TypeOfTask;

/**
 * Todo class that tracks a simple description of a task
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Todo extends Task {

    /**
     * Default constructor that takes in the description of the task
     *
     * @param description
     */
    public Todo(String description) {
        super(description, TypeOfTask.todo);
    }

    /**
     * Returns the description of the task
     *
     * @return description of task
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", super.getStatusIcon(), super.getDescription());
    }
}
