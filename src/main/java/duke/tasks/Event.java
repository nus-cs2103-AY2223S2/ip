package duke.tasks;


public class Event extends Task {

    protected String eventDetail;

    protected String time;


    /**
     * Initialises the object
     *
     * @param detail the description including task name of the task
     * @param time   the time of the task
     */
    public Event(String detail, String time) {
        super(detail);
        this.eventDetail = detail.trim();
        this.time = time;
    }

    /**
     * Outputs task details
     *
     * @return a string including the task name and duration
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + eventDetail + " (from: "
            + time.split("from ")[1].split(" to ")[0] + " to:"
            + time.split("from ", 2)[1].split(" to")[1] + ")";
    }

    /**
     * Gets the type of task
     *
     * @return a string "event"
     */
    public String getType() {
        return "E";
    }

    /**
     * Gets the type of task
     *
     * @return a String representing event detail
     */
    public String getDetail() {
        return this.eventDetail;
    }

    /**
     * Gets the time of task
     *
     * @return a String representing time
     */
    public String getTime() {
        return this.time;
    }
}
