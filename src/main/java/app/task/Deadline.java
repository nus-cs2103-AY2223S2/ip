package app.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for a Deadline Task. This constructor is currently under rework
     * to move the datetime parsing to a method under Task.
     * @param description
     * @param deadline
     * @throws InvalidDateTimeException
     */
    Deadline(String description, String deadline) throws InvalidDateTimeException {
        super(description);
        this.symbol = "D";

        for (DateTimeFormatter f : SUPPORTED_DATE_TIME_INPUT) {
            try {
                this.deadline = LocalDateTime.parse(deadline, f);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        if (Objects.isNull(this.deadline)) {
            throw new InvalidDateTimeException("Try reformatting your date/time to the supported formats:\n"
                    + "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm\n"
                    + "Make sure that the date/time is valid!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Formats the task as data to be stored in text file.
     * @return data format recognisable by the app
     */
    @Override
    public String asDataFormat() {
        return super.asDataFormat("by:" + this.deadline);
    }
}
