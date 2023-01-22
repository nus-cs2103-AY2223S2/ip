public class DeadlineTask extends Task {
    protected String by;

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     */
    private DeadlineTask(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public DeadlineTask(String description, String by) {
        this(description, false, by);
    }

    @Override
    public String serialise() {
        String serialisedString = super.serialise();
        StringBuilder stringBuilder = new StringBuilder(serialisedString);
        stringBuilder.insert(0, "D");
        stringBuilder.append(String.format(",%s", by));

        return stringBuilder.toString();
    }

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
