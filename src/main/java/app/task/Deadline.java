package app.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String MISSING_BY_ERROR = "Could you provide the deadline in "
            + "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm format please.";
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
        this.taskType = TaskTypes.Type.DEADLINE;

        if (isArgEmpty(deadline)) {
            throw new InvalidInputException(MISSING_BY_ERROR);
        }
        this.deadline = parseDate(deadline);
        this.fieldToValueMap.put("by", this.deadline.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
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

    private LocalDateTime getDeadline() {
        return this.deadline;
    }
    @Override
    public int compareTo(Task other) {
        int result = this.getType().compareTo(other.getType());
        if (result == 0) {
            Deadline otherDeadline = (Deadline) other;
            return (this.deadline.compareTo(otherDeadline.getDeadline()));
        } else {
            return result;
        }
    }
}
