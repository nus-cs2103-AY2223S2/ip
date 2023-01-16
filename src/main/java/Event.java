public class Event extends Task {

    private String startTime;
    private String endTime;

    private Event(String description, String start, String end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Factory method to create an Event task based on commands.
     * @param input command input
     * @return an Event task object based on command input
     */
    public static Event create(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new IncompleteCommandException("Incomplete arguments for command event, I have found", null);
        }
        String eventDescription = input.substring(6, fromIndex - 1);
        String startTime = input.substring(fromIndex + 6, toIndex - 1);
        String endTime = input.substring(toIndex + 4);
        return new Event(eventDescription, startTime, endTime);
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
