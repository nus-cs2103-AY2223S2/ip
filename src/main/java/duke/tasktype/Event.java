package duke.tasktype;

/**
 * The class for Event tasks.
 */
public class Event extends Task {
    protected String time1;
    protected String time2;

    /**
     * The constructor of Event class.
     *
     * @param cont the content of the task
     * @param time1 the start time of the task
     * @param time2 the end time of the task
     */
    public Event(String cont, String time1, String time2) {
        super(cont);
        this.time1 = time1;
        this.time2 = time2;
    }

    /**
     * Override the toString() method to show the information of the Event task.
     *
     * @return a String that shows the information of the Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.time1 + " to: " + this.time2 + ")";
    }
}
