package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;
    static final String INDICATOR = "[Event]";
    static final String INPUT_PREFIX = "event ";
    static final String FORMAT_EXCEPTION_MESSAGE = "Invalid format for creating Event duke.Task";
    private static final String INVALID_DATE_EXCEPTION = "Incompatible date format given for start/end of Event";
    static final String PERIOD_BEGIN_PREFIX = "/from ";
    static final String PERIOD_BEGIN_PREFIX_REPLACEMENT = "FROM: ";
    static final String PERIOD_END_PREFIX = "/to ";
    static final String PERIOD_END_PREFIX_REPLACEMENT = " |TO: ";
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

    static EventTask createEvent(String text) throws DukeException {
        int firstPrefixIndex = text.indexOf(PERIOD_BEGIN_PREFIX);
        int secondPrefixIndex = text.indexOf(PERIOD_END_PREFIX);
        if (firstPrefixIndex == -1 || secondPrefixIndex == -1) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
        }
        try {
            String taskName = text.substring(INPUT_PREFIX.length(), firstPrefixIndex - 1);
            String startDateString = text.substring(firstPrefixIndex + PERIOD_BEGIN_PREFIX.length(), secondPrefixIndex - 1);
            String endDateString = text.substring(secondPrefixIndex + PERIOD_END_PREFIX.length());
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            return new EventTask(taskName, startDate, endDate);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(FORMAT_EXCEPTION_MESSAGE);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATE_EXCEPTION);
        }
    }


    @Override
    public String toString() {
        return INDICATOR + super.toString() + formattedPeriod(startDate, endDate);
    }
}
