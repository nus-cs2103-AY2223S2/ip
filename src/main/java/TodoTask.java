public class TodoTask extends Task{

    /**
     * Constructor to create a new instance of Task.
     *
     * @param description Title of the task
     * @param isDone True if task is completed.
     */
    private TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     */
    public TodoTask(String description) {
        this(description, false);
    }

    /**
     * Serialise task to be saved in local storage.
     * @return Serialised string of this task.
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
     * @return An instance of TodoTask.
     */
    public static Task deserialise(String data) {
        String[] args = data.split(",");

        boolean taskDone = args[1].equals("Y");
        String taskDesc = args[2];

        return new TodoTask(taskDesc, taskDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
