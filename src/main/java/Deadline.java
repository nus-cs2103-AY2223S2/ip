public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
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
