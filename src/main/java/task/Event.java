package task;

import duke.DukeException;

import java.time.LocalDate;

import ui.Parser;

/**
 * The event class that extends the Task class.
 * An even should have a start and an end date.
 */
public class Event extends Task {
    protected static final String START_DATE_KEYWORD = "/from";
    protected static final String END_DATE_KEYWORD = "/to";
    protected LocalDate startTime;
    protected LocalDate endTime;

    /**
     * The default constructor
     *
     * @param description the content of the command
     * @throws DukeException when the input command cannot be parsed
     */
    public Event(String description) throws DukeException {
        super();
        this.name = parseEventName(description);
        this.startTime = parseStartTime(description);
        this.endTime = parseEndTime(description);
        this.type = "E";
    }

    /**
     * Parses the event name from user description
     *
     * @param description user-input description
     * @return the event name
     * @throws DukeException when the name is absent
     */
    private String parseEventName(String description) throws DukeException {
        int indexOfFrom = getIndexOfFrom(description);
        return description.substring(0, indexOfFrom - " ".length());
    }

    /**
     * Parses the index of the start date keyword from user description
     * @param description user-input description
     * @return the index of the keyword of start date keyword
     * @throws DukeException when the keyword is missing
     */
    private int getIndexOfFrom(String description) throws DukeException {
        return getKeywordIndex(description, START_DATE_KEYWORD);
    }

    /**
     * Parses the index of the end date keyword from user description
     * @param description user-input description
     * @return the index of the keyword of end date keyword
     * @throws DukeException when the keyword is missing
     */
    private int getIndexOfTo(String description) throws DukeException {
        return getKeywordIndex(description, END_DATE_KEYWORD);
    }

    /**
     * Parses the start time from the string description
     * @param description the user-input description
     * @return the start time of the event
     * @throws DukeException when the command is incomplete
     */
    private LocalDate parseStartTime(String description) throws DukeException {
        int indexOfFrom = getIndexOfFrom(description);
        int indexOfTo = getIndexOfTo(description);
        return Parser.parseDate(
                description.substring(indexOfFrom + (START_DATE_KEYWORD + " ").length(),
                        indexOfTo - " ".length()));
    }

    /**
     * Parses the end time from the string description
     * @param description the user-input description
     * @return the end time of the event
     * @throws DukeException when the command is incomplete
     */
    private LocalDate parseEndTime(String description) throws DukeException {
        return Parser.parseDate(
                description.substring(
                        description.indexOf(END_DATE_KEYWORD) + (END_DATE_KEYWORD + " ").length()));
    }

    /**
     * Overriding the toString class
     *
     * @return the string representation of an event
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", type,
                super.toString(), Task.printDate(startTime), Task.printDate(endTime));
    }
}
