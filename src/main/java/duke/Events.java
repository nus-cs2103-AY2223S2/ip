package duke;

/**
 * A task type that the chatting bot can create.
 */
public class Events extends Task {
    private String strtime;
    private String endtime;

    /**
     * The constructor of this class.
     *
     * @param name
     * @param strtime
     * @param endtime
     */
    public Events(String name, String strtime, String endtime) {
        super(name);
        this.strtime = strtime;
        this.endtime = endtime;
    }

    /**
     * The method that returns the starting time of the event.
     *
     * @return the starting time of the event.
     */
    public String getStrtime() {
        return this.strtime;
    }

    /**
     * The method that returns the ending time of the event.
     *
     * @return the ending time of the event.
     */
    public String getEndtime() {
        return this.endtime;
    }

    /**
     * The toString method.
     *
     * @return the task name with the specific times of the event and status.
     */
    public String toString() {
        if (this.getStatus()) {
            return "[E][X] " + this.getName() + " (from: " + this.strtime + "to: " + this.endtime + ")";
        } else {
            return "[E][ ] " + this.getName() + " (from: " + this.strtime + "to: " + this.endtime + ")";
        }
    }
}
