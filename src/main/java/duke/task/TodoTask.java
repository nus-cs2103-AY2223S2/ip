package duke.task;

import java.time.LocalDate;

/**
 * The class representing a {@code Todo} task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class TodoTask extends DukeTask{
    public TodoTask(String s) {
        super(s);
    }

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
