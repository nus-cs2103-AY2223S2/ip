public class TodoTask extends Task{

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     */
    private TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public TodoTask(String description) {
        this(description, false);
    }

    @Override
    public String serialise() {
        String serialisedString = super.serialise();
        StringBuilder stringBuilder = new StringBuilder(serialisedString);
        stringBuilder.insert(0, "T");

        return stringBuilder.toString();
    }

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
