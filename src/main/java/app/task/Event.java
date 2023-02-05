package app.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for an Event Task. This constructor is under rework to move
     * the format parsing sections into a method under Task.
     * @param description
     * @param from
     * @param to
     * @throws InvalidDateTimeException
     */
    Event(String description, String from, String to) throws InvalidDateTimeException {
        super(description);
        this.symbol = "E";

        for (DateTimeFormatter f : SUPPORTED_DATE_TIME_INPUT) {
            try {
                this.from = LocalDateTime.parse(from, f);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateTimeFormatter f : SUPPORTED_DATE_TIME_INPUT) {
            try {
                this.to = LocalDateTime.parse(to, f);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        if (Objects.isNull(this.from) || Objects.isNull(this.to)) {
            throw new InvalidDateTimeException("Try reformatting your date/time to the supported formats:\n"
                    + "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm");
        }
    }

    /**
     * Formats the task as data to be stored in text file.
     * @return data format recognisable by the app
     */
    @Override
    public String asDataFormat() {
        return super.asDataFormat("from:" + this.from, "to:" + this.to);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(OUTPUT_FORMAT)
                + ", to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

}
