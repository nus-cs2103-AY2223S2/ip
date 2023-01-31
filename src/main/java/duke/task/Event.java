package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected final LocalDateTime from;
    protected final LocalDateTime to;
    /**
     * 
     * @param name: a string indicating thename of the duke.task.Event task
     * @param status: a String that checks if the duke.task.Event is done or not
     * @param from: a string representing the starting time passed in by the user
     * @param to: a string representing the ending time passed in by the user
     */
    public Event(String name, int status, String from, String to) {
        super(name, status);
        this.from = Parser.formatDateTime(from);
        this.to = Parser.formatDateTime(to);
    }

    private int getStatusNo() {
        if (super.status.equals("[ ]")) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * overrrides toString method
     */
    public String toString() {
        return String.format("[E]%s %s (from %s to %s)", status, name, Parser.TransformDateTime(from),
        Parser.TransformDateTime(to));
    }
    
    public String toStoreFormatString() {
        return String.format("E/%s/%d/%s/%s", super.name, this.getStatusNo(), Parser.reverseFormatDateTime(from), Parser.reverseFormatDateTime(to));
    }
}
