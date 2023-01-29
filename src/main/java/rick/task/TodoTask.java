package rick.task;

import java.time.LocalDate;

/**
 * The class representing a {@code TodoTask} task.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TodoTask extends RickTask {
    /**
     * The default constructor that initialises a TodoTask with the given description.
     * @param s The task description.
     */
    public TodoTask(String s) {
        super(s);
    }

    /**
     * Human friendly interpretation of this task.
     *
     * @return The task interpretation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats this task into a format for storage in the Storage class.
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
     * Indicate if this task falls on the given date.
     * @return False. It has no relation to any date.
     */
    @Override
    public boolean isOnDate(LocalDate dt) {
        return false;
    }
}
