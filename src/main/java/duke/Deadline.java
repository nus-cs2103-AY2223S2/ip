package duke;

import java.time.LocalDateTime;

/**
 * Represents a Deadline entered by the user.
 */
class Deadline extends Task {
    private final LocalDateTime byTime;

    /**
     * Constructor for the Deadline.
     *
     * @param taskDescription Description of the Deadline.
     * @param byTime The Time and Date the deadline is due.
     */
    public Deadline(String taskDescription, LocalDateTime byTime) {
        this.taskDescription = taskDescription;
        this.byTime = byTime;
    }


    /**
     * toString for the Deadline.
     *
     * @param taskDescription Description of the Deadline.
     * @param byTime The Time and Date the deadline is due.
     */
    @Override
    public String toString() {
        return String.format("[D]%s by: %s", super.toString(), byTime.format(formatter));
    }
}
