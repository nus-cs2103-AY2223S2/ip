public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory method to create a Deadline task based on commands.
     * @param input command input
     * @return a Deadline task object based on command input
     */
    public static Deadline create(String input) {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new IncompleteCommandException("Incomplete arguments for command deadline, I have found", null);
        }
        String deadline = input.substring(byIndex + 1);
        String deadlineDescription = input.substring(9, byIndex - 1);
        return new Deadline(deadlineDescription, deadline);
    }

    public static Deadline create(String description, String deadline, String marked) {
        Deadline task = new Deadline(description, deadline);
        if (marked.equals("1")) {
            task.markSilent();
        }
        return task;
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    @Override
    public void mark() {
        super.mark();
        System.out.println(String.format(" [%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline));
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    @Override
    public void unmark() {
        super.unmark();
        System.out.println(String.format(" [%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline));
    }

    /**
     * Method for child classes to return their type.
     * @return string type of task
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * String representation of the Deadline Task.
     * @return string representation of the Deadline Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline);
    }
}
