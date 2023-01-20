package duke.task;

import duke.exception.DukeException;
import duke.utils.BooleanUtils;
import duke.utils.LocalDateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Creates a Deadline object.
     *
     * @param isDone Is the task done.
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);

        this.deadline = deadline;
    }

    /**
     * Returns a Deadline object created using the specified data that was loaded from storage.
     *
     * @param args Data about the deadline that was loaded from storage.
     * @return The Deadline object created using data loaded from storage.
     * @throws DukeException Indicates missing data or incorrect data type in args.
     */
    public static Deadline createFromStorage(String[] args) throws DukeException {
        if (args.length != 4) {
            throw new DukeException("Failed to load a deadline from storage due to missing data.");
        }

        if (!BooleanUtils.isBooleanString(args[1])) {
            throw new DukeException("Failed to load a deadline from storage due to incorrect data type.");
        }

        args = Task.formatStringsFromStorage(args);

        return new Deadline(Boolean.parseBoolean(args[1]), args[2], args[3]);
    }

    @Override
    public String getStorageString() {
        return String.format("D | %s | %s", super.getStorageString(), Task.formatStringForStorage(deadline));
    }

    @Override
    public String toString() {
        String deadlineStr = deadline.format(LocalDateTimeUtils.outputDateTimeFormatter);

        return String.format("[D]%s (by: %s)", super.toString(), deadlineStr);
    }
}
