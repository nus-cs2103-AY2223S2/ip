package duke.task;

import java.time.LocalDateTime;

import duke.UI.UI;
/**
 * The Event of tasks.
 * Inherits from the superclass Task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected  LocalDateTime to;

    /**
     * The constructor of Event task.
     * @param taskDescription Name of the task.
     * @param from Start of the event.
     * @param to End of the event.
     */
    public Event(String taskDescription, LocalDateTime from, LocalDateTime to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
        super.typeOfTask = 'E';
    }

    @Override
    public String savedTaskFormat() {
        return  super.savedTaskFormat() + " | " + from + " to " + to;
    }

    @Override
    public String toString() {
        return "[" + super.typeOfTask + "]" + super.toString() + "(from: " + UI.getOutputDateFormat(from) +
                " to: " + UI.getOutputDateFormat(to) + ")";
    }




}
