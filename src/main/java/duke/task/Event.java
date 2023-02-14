package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;

/**
 * An event task object that stores its description, the start and end date of the event and on whether is it done or
 * not. The task can be marked as done or unmarked as not done.
 */
public class Event extends Task {

    /**
     * The start date of the event.
     */
    private final LocalDate from;
    /**
     * The end date of the event.
     */
    private final LocalDate to;

    /**
     * Constructor for an event task with the given description, start and end date. It checks on whether the start
     * and end dates are in the appropriate format first. If it is not, an exception is throw to provide hints on the
     * start and end dates format.
     *
     * @param description The description of the event task
     * @param from        The start date of the event
     * @param to          The end date of the event
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw DukeException.DATETIME_FORMAT;
        } catch (Exception e) {
            throw new DukeException("Unknown error occurred when parsing datetime.");
        }
    }

    /**
     * Constructor for a deadline task with the given description, start date, end date and its status of completion.
     * It checks on whether the start and end dates are in the appropriate format first. If it is not, an exception
     * is throw to provide hints on the start and end dates format.
     *
     * @param description The description of the event task
     * @param from        The start date of the event
     * @param to          The end date of the event
     * @param isMarked    Whether the event task is done or not
     */
    public Event(String description, String from, String to, boolean isMarked) {
        this(description, from, to);
        isDone = isMarked;
    }

    /**
     * Checks on whether the string representation of the event task is the same format as the one exported. If it is,
     * then a new task is created with the described properties. Otherwise, return an empty task.
     *
     * @param data String representation of an event task
     * @return An event task object that describes the given data of the task
     */
    public static Task readFromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*) ; (from:) (.*) ; (to:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1");
            String description = matcher.group(4);
            String from = matcher.group(6);
            String to = matcher.group(8);
            return new Event(description, from, to, isMarked);
        }
        return Task.EMPTY_TASK;
    }

    @Override
    public String toString() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), formattedFrom, formattedTo);
    }

    @Override
    public String toData() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("Event | marked: %s ; description: %s ; from: %s ; to: %s", getMarkedStatus(),
                description, formattedFrom, formattedTo);
    }
}
