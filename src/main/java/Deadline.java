public class Deadline extends Task{

    protected String eventDetail;

    protected String timeDescription;

    protected String time;

    /**
     * Constructor
     * @param description a string description of the entire description of the task
     */
    public Deadline(String description) {
        super(description);
        String detail = description.split("/")[0].split(" ", 2)[1];
        this.eventDetail = detail;
        String timeDescription = description.split("/")[1].split(" ")[0] + ": " + description.split("/")[1].split(" ",2)[1];
        this.timeDescription = timeDescription;
        this.time = timeDescription.split(": ",2)[1];
    }

    /**
     * String representation of the task details
     * @return a string including the task name and time
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + eventDetail + "(" + timeDescription + ")";
    }
    /**
     * Gets the type of task
     * @return a string "deadline"
     */
    public String getType() {
        return "D";
    }
    /**
     * Gets the type of task
     * @return a String representing event detail
     */
    public String getDetail() {
        return this.eventDetail;
    }
    /**
     * Gets the time of task
     * @return a String representing time
     */
    public String getTime() {
        return this.time;
    }

}
