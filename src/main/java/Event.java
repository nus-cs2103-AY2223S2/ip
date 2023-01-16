public class Event extends Task {

    private String startTime;
    private String endTime;

    public Event(String description, String start, String end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    @Override
    public void mark() {
        super.mark();
        System.out.println(String.format(" [%s][%s] %s (from: %s to: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startTime,
                this.endTime));
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    @Override
    public void unmark() {
        super.unmark();
        System.out.println(String.format(" [%s][%s] %s (from: %s to: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startTime,
                this.endTime));
    }

    /**
     * Method for child classes to return their type.
     * @return string type of task
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * String representation of the Event Task.
     * @return string representation of the Event Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.startTime,
                this.endTime);
    }
}
