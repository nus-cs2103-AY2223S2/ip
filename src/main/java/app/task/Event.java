package app.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String MISSING_FROM_OR_TO_ERROR =
            "Plz provide BOTH the 'from' and 'to' in "
            + "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm format.";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for Event Task.
     * @param description
     * @param from
     * @param to
     * @throws InvalidDateTimeException for invalid from and to formats.
     * @throws InvalidInputException for any empty arguments.
     */
    Event(String description, String from, String to)
            throws InvalidDateTimeException, InvalidInputException {
        super(description);
        this.symbol = "E";
        this.taskType = TaskTypes.Type.EVENT;

        if (isArgEmpty(from) || isArgEmpty(to)) {
            throw new InvalidInputException(MISSING_FROM_OR_TO_ERROR);
        }
        this.from = parseDate(from);
        this.to = parseDate(to);

        this.fieldToValueMap.put("from", this.from.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        this.fieldToValueMap.put("to", this.from.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
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

    @Override
    public int compareTo(Task other) {
        int result = this.getType().compareTo(other.getType());
        if (result == 0) {
            return this.eventCompare(other);
        } else {
            return result;
        }
    }

    private int eventCompare(Task other) {
        Event otherEvent = (Event) other;
        int fromDiff = this.from.compareTo(otherEvent.from);
        if (fromDiff == 0) {
            int toDiff = this.to.compareTo(otherEvent.to);
            return toDiff;
        } else {
            return fromDiff;
        }
    }
}
