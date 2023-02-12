package rick.task;

import java.time.LocalDate;

/**
 * Represents a Todo task.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TodoTask extends RickTask {
    /**
     * Constructs and initialises a TodoTask with the given description.
     * @param s The task description.
     */
    public TodoTask(String s) {
        super(s);
    }

    /**
     * Generates and returns a human friendly interpretation of this task.
     *
     * @return The task interpretation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats this task into a format for storage in the Storage class, and
     * returns the formatted schema.
     *
     * @return The formatted task.
     */
    @Override
    public String toDbSchema() {
        return String.format(
                "%s|%s",
                "T",
                super.toDbSchema()
        );
    }

    /**
     * Returns a boolean indicating if this task falls on the given date.
     *
     * @return False. It has no relation to any date.
     */
    @Override
    public boolean isOnDate(LocalDate dt) {
        return false;
    }
}
