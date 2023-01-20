public class Event extends Task{

    protected String eventDetail;

    protected String time;

    /**
     * Constructor
     * @param description the entire description including task name and time of the task
     */
    public Event(String description) {
        super(description);
        String detail = description.split("/")[0].split(" ", 2)[1];
        this.eventDetail = detail;
        String time = description.split("/")[1].split(" ")[0] + ": " + description.split("/")[1].split(" ",2)[1] +
                description.split("/")[2].split(" ")[0] + ": " + description.split("/")[2].split(" ",2)[1];
        this.time = time;

    }

    /**
     * String representation of the task details
     * @return a string including the task name and duration
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + eventDetail + "(" + time +  ")";
    }

    /**
     * Get the type of task
     * @return a string "event"
     */
    public String getType() {
        return "event";
    }

}
