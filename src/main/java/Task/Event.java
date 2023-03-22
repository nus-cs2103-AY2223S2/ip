package Task;

import DukeException.DukeException;
import DukeException.TaskFormatException;
import duke.Times;

/**
 * Object class of Event
 * Event must have two time attribute
 */
public class Event extends Task {
    protected static final int EVENT = 5;
    protected static final int FROM = 6; // /from/n = 6
    protected static final int TO = 4; // /to/n = 4
    protected Times from;
    protected Times to;
    protected String type = "[E]";
    /**
     * Constructor for Event
     * @param description -> Task description
     * @param from -> String format of starting time of event
     * @param to -> String format of ending time of event
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = new Times(from);
        this.to = new Times(to);
    }

    /**
     * Creates event object
     * Format: event {description} /from {time} /to {time}
     * Example: event carnival /from 2019-10-15 1530 /to 2020-12-11 1200
     * @param input -> Input Chat which starts with "event"
     * @return Event object
     * @throws DukeException if format of event task is wrong.
     */
    public static Event createEvent(String input) throws DukeException {
        int index1 = input.indexOf("/");
        int index2 = input.lastIndexOf("/");
        // format is wrong, there are less than 2 "/"
        if (index1 == -1 || index2 == -1 || index1 == index2) {
            DukeException e = new TaskFormatException();
            throw e;
        }
        String description = input.substring(EVENT + 1, index1 - 1);
        String from = input.substring(index1 + FROM, index2 - 1);
        String to = input.substring(index2 + TO);
        Event temp = new Event(description, from, to);
        return temp;
    }

    @Override
    public String getDescriptionAndTime() {
        return description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toString() {
        return type + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
