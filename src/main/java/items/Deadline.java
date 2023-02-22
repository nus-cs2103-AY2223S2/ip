package items;

import java.time.LocalDate;

/**
 * Represents a Deadline task
 * @author clydelhui
 */
public class Deadline extends Task {
    private final LocalDate endDate;

    /**
     * Creates a <code>Deadline</code> with the given description and end date
     * @param description The description of the <code>Deadline</code>
     * @param endDate the end date of the <code>Deadline</code>
     */
    public Deadline(String description, LocalDate endDate) {
        super(description, "D");
        this.endDate = endDate;
    }

    /**
     * Creates a <code>Deadline</code> with the given description, status and end date
     * @param description The description of the <code>Deadline</code>
     * @param isDone A boolean which indicates if the <code>Deadline</code> is done
     * @param endDate
     */
    public Deadline(String description, boolean isDone, LocalDate endDate) {
        super(description, "D", isDone);
        this.endDate = endDate;
    }

    @Override
    public String generateStorageForm() {
        return this.getTaskType() + "@" + this.getDescription() + "@"
                + this.getStatusIcon() + "@" + this.endDate;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + "[" + this.getStatusIcon() + "]"
                + this.description + "/" + this.endDate;
    }
}
