package Week2.src.main;


/**
 * A subclass of Task
 * Contains the occuring time information of an event
 */



/**
 * A subclass that extends Task.
 * Event contains its occuring time (start time and end time)
 */
public class Event extends Task {
    String from;
    String to;

    /**
     * Event constructor

     * @param content content of the task
     * @param from starting time of the task
     * @param to end time of the task

     * @param content Task content
     * @param from Start time of the event
     * @param to End time of the event

     */
    Event(String content, String from, String to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    /**

     * Overrides toString() method to change the task information to a string format.
     * @return A string format of given task data

     * It overrides toString() method to change information to a string format
     * @return String format of the event task data

     */
    @Override
    public String toString() {

        return "[E][" + this.getDone() + "] " + this.content + "(from:" + this.from + " to:" + this.to + ")";

    }
}
