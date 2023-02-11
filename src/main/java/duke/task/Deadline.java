package duke.task;

import java.time.LocalDateTime;

/**
 * A Deadline is a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs an unchecked Deadline with a title and deadline.
     * @param title The title of the Deadline task.
     * @param deadline A datetime string in ISO 8601 format.
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = super.parseDateString(deadline);
    }

    /**
     * Constructs a Deadline with a title and deadline that is checked or
     * unchecked depending on the value of isDone.
     * @param title The title of the Deadline task.
     * @param deadline A datetime string in ISO 8601 format.
     * @param isDone Boolean to indicate if the Deadline should be checked or not.
     */
    public Deadline(String title, String deadline, boolean isDone) {
        super(title, isDone);
        this.deadline = super.parseDateString(deadline);
    }

    /**
     * Returns the disk format representation of the Deadline object.
     * @return Deadline object in disk format.
     */
    @Override
    public String toDiskFormat() {
        return String.format("D|%d|%s|%s", super.getIsDone() ? 1 : 0, super.getTitle(),
                super.dateTimeToDiskFormat(this.deadline));
    }

    /**
     * Returns a printable version of the Deadline object. It contains the title,
     * deadline and an indicator of whether it is checked or not.
     * @return The string representation of this Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[%s][D] %s (by: %s)", super.getIsDone() ? "X" : " ",
                super.getTitle(), super.dateTimeToString(this.deadline));
    }
}
