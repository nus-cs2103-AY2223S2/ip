package duke;

/**
 * class for tasks with a start and end time
 */
public class Event extends Task {
    private static final String TASK_TYPE = "[E]";
    private String from;
    private String to;

    /**
     * constructor for new Event instance
     * 
     * @param description description of event
     * @param from        start time of event as a String
     * @param to          end time of event as a String
     * @throws MissingDescriptionException missing description
     */
    public Event(String description, String from, String to) throws MissingDescriptionException {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * returns string representation of event
     * 
     * @return string with type, completion status, task description, start time of
     *         task, and end time of task
     */
    @Override
    public String toString() {
        return TASK_TYPE + super.toString() + " (from: " + from + " to:" + to + ")";
    }

    /**
     * returns string representation of event to be saved into the text file
     * 
     * @return string representation of event to be saved into text file
     */
    @Override
    public String toStorageData() {
        String completionStatus = getStatusIcon();
        return TASK_TYPE + "//" + completionStatus + "//" + description + "//" + from + "//" + to;
    }
}
