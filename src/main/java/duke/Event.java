package duke;

/**
 * Models an event which is a task
 */
public class Event extends Task {

    /** String used to represent the start time of event. */
    protected String taskStartTime;
    /** String used to represent the end time of event. */
    protected String taskEndTime;
    /** String used to assign the name of the event. */
    protected String taskDesc;

    /**
     * Constructor for the Event class.
     *
     * @param taskDesc The name of the task.
     * @param taskStartTime The starting time of event.
     * @param taskEndTime The ending time of event.
     */
    public Event(String taskDesc, String taskStartTime, String taskEndTime) {
        super(taskDesc);
        this.taskDesc = taskDesc;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

    /**
     * Overloaded constructor for the Event class.
     *
     * @param taskDesc The name of the task.
     * @param taskStartTime The starting time of event.
     * @param taskEndTime The ending time of event.
     * @param taskStatus The status of the task.
     */
    public Event(String taskDesc, String taskStartTime, String taskEndTime, boolean taskStatus) {
        super(taskDesc, taskStatus);
        this.taskDesc = taskDesc;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + taskStartTime + " to: " + taskEndTime + ")";
    }

    /**
     * {@inheritDoc}
     */
    public String asCsv() {
        if (super.taskStatus) {
            return "E,1," + taskDesc + "," + taskStartTime + "," + taskEndTime;
        } else {
            return "E,0," + taskDesc + "," + taskStartTime + "," + taskEndTime;
        }
    }
}
