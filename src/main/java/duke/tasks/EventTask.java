package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.DukeException;

/**
 * Class representing an Event task
 */
public class EventTask extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;
    static final String INDICATOR = "[Event]";
    private static final String INVALID_DATE_EXCEPTION = "Incompatible date format given for start/end of Event";
    static final String PERIOD_BEGIN_PREFIX_REPLACEMENT = "FROM: ";
    static final String PERIOD_END_PREFIX_REPLACEMENT = " | TO: ";
    EventTask(String name, LocalDate startDate, LocalDate endDate) throws DukeException {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private static String formattedPeriod (LocalDate startDate, LocalDate endDate) {
        // TODO: Abstract into util function
        String startDateString =  startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String endDateString =  endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format(Task.EXTRAS_FORMAT_TEMPLATE, PERIOD_BEGIN_PREFIX_REPLACEMENT + startDateString
                + PERIOD_END_PREFIX_REPLACEMENT + endDateString);
    }

    public static EventTask createTask(String taskName, String startDateString, String endDateString) throws DukeException {
        try {
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            return new EventTask(taskName, startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATE_EXCEPTION);
        }
    }

    @Override
    public String toString() {
        return INDICATOR + super.toString() + formattedPeriod(startDate, endDate);
    }
}
