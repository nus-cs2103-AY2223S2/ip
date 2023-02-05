package app.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for Deadline Task.
     * @param description
     * @param deadline
     * @throws InvalidInputException for any empty inputs.
     * @throws InvalidDateTimeException for a badly formatted datetime.
     */
    Deadline(String description, String deadline)
            throws InvalidInputException, InvalidDateTimeException {
        super(description);
        this.symbol = "D";

        if (isArgEmpty(deadline)) {
            throw new InvalidInputException("plz provide the deadline in "
                    + "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm format");
        }
        this.deadline = parseDate(deadline);
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
