public class DeadlineTask extends Task {
    protected String by;

    /**
     * Constructor to create a new instance of Task.
     *
     * @param description Title of the task
     * @param isDone True if task is completed.
     * @param by Deadline of this task.
     */
    private DeadlineTask(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     * @param by Deadline of this task.
     */
    public DeadlineTask(String description, String by) {
        this(description, false, by);
    }

    /**
     * Serialise task to be saved in local storage.
     * @return Serialised string of this task.
     */
    @Override
    public String serialise() {
        String serialisedString = super.serialise();
        StringBuilder stringBuilder = new StringBuilder(serialisedString);
        stringBuilder.insert(0, "D");
        stringBuilder.append(String.format(",%s", by));

        return stringBuilder.toString();
    }

    /**
     * Returns an instance of the task represented by the given data.
     * @param data The serialised string of the task.
     * @return An instance of DeadlineTask.
     */
    public static Task deserialise(String data) {
        String[] args = data.split(",");

        boolean taskDone = args[1].equals("Y");
        String taskDesc = args[2];
        String taskBy = args[3];

        return new DeadlineTask(taskDesc, taskDone, taskBy);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
