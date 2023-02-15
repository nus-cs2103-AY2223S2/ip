package elise.tasks;

import elise.MaybeDate;
import elise.internal.Parser;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private MaybeDate begin;
    private MaybeDate end;

    /**
     * Constructor for Event.
     *
     * @param status Completed or not.
     * @param content Contains message, start and end dates.
     */
    public Event(boolean status, String[] content) {
        super(status, content[0]);
        this.begin = Parser.parseDate(content[1]);
        this.end = Parser.parseDate(content[2]);
    }

    /**
     * Returns the type icon dedicated to the event task.
     *
     * @return Type icon of event task
     */
    @Override
    protected String getTypeIcon() {
        return "E";
    }
    /**
     * Returns String representation of the event task
     *
     * @return String representation of the event task
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + begin + " to: " + end + ")";
    }

    /**
     * Returns string representation of task to be stored in file.
     *
     * @return String representation of event task to be stored in file.
     */
    @Override
    public String fileMessage() {
        return String.format("%s||%d||%s||%s||%s\n", getTypeIcon(), isDone ? 1 : 0, content, begin, end);
    }
}
