public class EventTask extends Task{
    protected String from;
    protected String to;

    /**
     * Constructor to create a new instance of Task.
     *
     * @param description Title of the task.
     * @param isDone True if task is completed.
     * @param from Start date of this task.
     * @param to End date of this task.
     */
    private EventTask(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task.
     * @param from Start date of this task.
     * @param to End date of this task.
     */
    public EventTask(String description, String from, String to) {
        this(description, false, from, to);
    }

    /**
     * Serialise task to be saved in local storage.
     * @return Serialised string of this task.
     */
    @Override
    public String serialise() {
        String serialisedString = super.serialise();
        StringBuilder stringBuilder = new StringBuilder(serialisedString);
        stringBuilder.insert(0, "E");
        stringBuilder.append(String.format(",%s,%s", from, to));

        return stringBuilder.toString();
    }

    /**
     * Returns an instance of the task represented by the given data.
     * @param data The serialised string of the task.
     * @return An instance of EventTask.
     */
    public static Task deserialise(String data) {
        String[] args = data.split(",");

        boolean taskDone = args[1].equals("Y");
        String taskDesc = args[2];
        String taskFrom = args[3];
        String taskTo = args[4];

        return new EventTask(taskDesc, taskDone, taskFrom, taskTo);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
