public class Event extends Task {
    private String startTime;
    private String endTime;

    /** 
     * A public constructor to initialize Event instance.
     * 
     * @param task Task name.
     * @param startTime Task start time.
     * @param endTime Task end time.
     */
    Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /** 
     * Returns the description of Event.
     * 
     * @return Event description.
     */
    @Override
    public String getDescription() {
        return "event " + super.getTaskName() + "/from" + this.startTime + "/to" + this.endTime;
    }

    /** 
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.startTime + "to:" + this.endTime + ")";
    }
}