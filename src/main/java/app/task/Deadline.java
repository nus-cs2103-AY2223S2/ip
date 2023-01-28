package app.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Deadline extends Task {
    private LocalDateTime deadline;

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

    @Override
    public String asDataFormat() {
        return super.asDataFormat("by:" + this.deadline);
    }
}
