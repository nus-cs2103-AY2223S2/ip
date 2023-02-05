package app.task;

import java.time.LocalDateTime;

public class Event extends Task {
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

        if (isArgEmpty(from) || isArgEmpty(to)) {
            throw new InvalidInputException("plz provide BOTH the 'from' and 'to' in "
                    + "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm format");
        }
        this.from = parseDate(from);
        this.to = parseDate(to);
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
