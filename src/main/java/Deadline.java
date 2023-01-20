public class Deadline extends Task{

    protected String eventDetail;

    protected String time;

    /**
     * Constructor
     * @param description a string description of the entire description of the task
     */
    public Deadline(String description) {
        super(description);
        String detail = description.split("/")[0].split(" ", 2)[1];
        this.eventDetail = detail;
        String time = description.split("/")[1].split(" ")[0] + ": " + description.split("/")[1].split(" ",2)[1];
        this.time = time;

    }

    /**
     * String representation of the task details
     * @return a string including the task name and time
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + eventDetail + "(" + time + ")";
    }

    /**
     * Get the type of task
     * @return a string "deadline"
     */
    public String getType() {
        return "deadline";
    }
}
