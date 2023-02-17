package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Class representing an Event task
 */
public class EventTask extends Task {
    static final String INDICATOR = "[Event]";
    static final String PERIOD_BEGIN_PREFIX_REPLACEMENT = "FROM: ";
    static final String PERIOD_END_PREFIX_REPLACEMENT = " | TO: ";
    private static final String INVALID_DATE_EXCEPTION = "Incompatible date format given for start/end of Event";

    private static final String DATE_FORMAT = "MMM dd yyyy";
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor for Event task
     *
     * @param name Task's name
     * @param startDate Start date for period allocated
     * @param endDate End date for period allocated
     * @throws DukeException If name is empty
     */
    EventTask(String name, LocalDate startDate, LocalDate endDate) throws DukeException {
        super(name);

        assert startDate != null;
        assert endDate != null;

        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Formats period allocated for Event task
     *
     * @param startDate start Date of period
     * @param endDate end Date of period
     * @return Formatted period
     */
    private static String formattedPeriod(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);

        // (FROM: DATE | TO: DATE)
        return String.format(Task.EXTRAS_FORMAT_TEMPLATE, PERIOD_BEGIN_PREFIX_REPLACEMENT + startDateString
                + PERIOD_END_PREFIX_REPLACEMENT + endDateString);
    }

    /**
     * Creates an Event task using String arguments
     *
     * @param taskName Task's name
     * @param startDateString Start date for allocated in String
     * @param endDateString End date for allocated in String
     * @return The created Event task
     * @throws DukeException If given date strings are of invalid format
     */
    public static EventTask createTask(String taskName, String startDateString, String endDateString)
            throws DukeException {
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
