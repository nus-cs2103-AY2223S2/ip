package jarvis.task;

import jarvis.exception.InvalidParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskFilter {
    private final LocalDate afterDate;
    private final LocalDate beforeDate;

    /**
     * Constructor for a task filter.
     * @param afterDate Date string where the search starts.
     * @param beforeDate Date string where the search ends
     * @throws InvalidParameterException If afterDate or beforeDate is invalid.
     */
    public TaskFilter(String afterDate, String beforeDate) throws InvalidParameterException {
        try {
            this.afterDate = afterDate == null ? null : LocalDate.parse(afterDate);
            this.beforeDate = beforeDate == null ? null : LocalDate.parse(beforeDate);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException(
                    "Invalid afterDate or beforeDate",
                    "I don't understand the given date."
            );
        }
    }

    /**
     * @return Whether this filter is empty.
     */
    public boolean isEmpty() {
        return this.afterDate == null && this.beforeDate == null;
    }

    /**
     * @return Date where the search starts.
     */
    public LocalDate getAfterDate() {
        return afterDate;
    }

    /**
     * @return Date where the search ends.
     */
    public LocalDate getBeforeDate() {
        return beforeDate;
    }
}
