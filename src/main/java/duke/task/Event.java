package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.helper.Parser;

import java.time.LocalDateTime;

/**
 * Subclass of tasks representing an event task
 */
public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    private Event(String description, String from, String to) throws InvalidDateTimeException {
        super(description, false, "E");

        startDateTime = Parser.handleDateTime(from);
        dueDateTime = Parser.handleDateTime(to);
    }

    /**
     * Factory method to create an event task
     *
     * @param desc the description of an event task
     * @return an event task
     * @throws InvalidDateTimeException If incorrect dateTime values are provided
     */
    public static Event createEvent(String desc) throws InvalidDateTimeException {
        String[] eventArr = desc.split(" /from ");
        String[] dataTimes = eventArr[1].split(" /to ");
        String eventDesc = eventArr[0].trim();
        String from = dataTimes[0].trim();
        String to = dataTimes[1].trim();
        return new Event(eventDesc, from, to);
    }

    /**
     * Returns the string representation of an event task
     *
     * @return string representation of an event task
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s %s to: %s %s)", super.toString(),
                startDateTime.toLocalDate(), startDateTime.toLocalTime(),
                dueDateTime.toLocalDate(), startDateTime.toLocalTime());
    }
}
