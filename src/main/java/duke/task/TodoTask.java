package duke.task;

/**
 * A task class representing a simple to do task
 * @author Junyi
 */
public class TodoTask extends Task {

    /**
     * Constructor to create a new instance of duke.task.Task.
     *
     * @param description Title of the task
     * @param isDone True if task is completed.
     */
    private TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructor to create a new instance of duke.task.Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     */
    public TodoTask(String description) {
        this(description, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialise() {
        String serialisedString = super.serialise();
        StringBuilder stringBuilder = new StringBuilder(serialisedString);
        stringBuilder.insert(0, "T");

        return stringBuilder.toString();
    }

    /**
     * Returns an instance of the task represented by the given data.
     * @param data The serialised string of the task.
     * @return An instance of duke.task.TodoTask.
     */
    public static Task deserialise(String data) {
        String[] args = data.split(",");

        boolean taskDone = args[1].equals("Y");
        String taskDesc = args[2];

        return new TodoTask(taskDesc, taskDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
