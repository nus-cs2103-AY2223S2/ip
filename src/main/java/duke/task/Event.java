package duke.task;

/**
 * Class for Event object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */

public class Event extends Task {
    private String from;
    private String to;
    private String taskType = "E";
    /**
     * Constructor for Event.
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Edit the information of an Event object.
     * @param partToBeEdited
     * @param content
     */
    public void editInfo(String partToBeEdited, String content) {
        switch (partToBeEdited) {
        case "desc":
            this.description = content;
            break;
        case "from":
            this.from = content;
            break;
        case "to":
            this.to = content;
            break;
        default:
            break;
        }
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Message printed when a new Event task is added.
     *
     * @return String representing the Event task information.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}


