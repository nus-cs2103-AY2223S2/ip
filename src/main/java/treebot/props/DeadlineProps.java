package treebot.props;


import java.time.LocalDateTime;

/**
 * Represents the props needed to create a <code>Deadline</code> object
 */
public class DeadlineProps extends Props {
    private LocalDateTime deadline;

    /**
     * Constructs a <code>Deadline</code> object.
     * @param taskDescription
     * @param deadline
     */
    public DeadlineProps(String taskDescription, LocalDateTime deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;

    }

    /**
     * Returns the deadline in the LocalDateTime format.
     * @return LocalDateTime
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }


}
