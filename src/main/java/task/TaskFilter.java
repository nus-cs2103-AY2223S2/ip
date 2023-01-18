package task;

import exception.InvalidParameterException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskFilter {
    private final LocalDate afterDate;
    private final LocalDate beforeDate;

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

    public boolean isEmpty() {
        return this.afterDate == null && this.beforeDate == null;
    }

    public LocalDate getAfterDate() {
        return afterDate;
    }

    public LocalDate getBeforeDate() {
        return beforeDate;
    }
}
