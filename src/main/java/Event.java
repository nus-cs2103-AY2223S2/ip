public class Event extends Task {

    private String startTime;
    private String endTime;

    public Event(String description, String start, String end) {
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
        String[] splitInput = input.split(" ");
        splitInput = StringUtils.removeWhiteSpace(splitInput);
        int fromIndex = StringUtils.searchString(splitInput, "/from");
        int toIndex = StringUtils.searchString(splitInput, "/to");
        if (fromIndex == -1 || toIndex == -1 || fromIndex + 1 == toIndex || fromIndex == 1) {
            throw new IncompleteCommandException("Incomplete arguments for command event, I have found", null);
        } else if (fromIndex > toIndex) {
            throw new UnknownCommandException("This command I do not know, only :\n " +
                    "event <description> /from <start> /to <end>", null);
        }
        String eventDescription = StringUtils.joinString(splitInput, 1, fromIndex - 1);
        String startTime = StringUtils.joinString(splitInput, fromIndex + 1, toIndex - 1);
        String endTime = StringUtils.joinString(splitInput, toIndex + 1, splitInput.length - 1);
        return new Event(eventDescription, startTime, endTime);
    }

    public static Event create(String description, String from, String to, String marked) {
        Event task = new Event(description, from , to);
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

    @Override
    public String writeTask() {
        return String.format("%s %d %s from %s to %s",
                this.getTaskType(),
                super.isCompleted() ? 1 : 0,
                this.description,
                this.startTime,
                this.endTime);
    }
}
