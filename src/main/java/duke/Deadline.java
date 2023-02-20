package duke;

import java.time.LocalDate;

/**
 * represents a task of deadline type
 */

public class Deadline extends Task {

    /**
     * initialises an deadline object with the given description
     * 
     * @param description
     *        description of deadline
     */

    public Deadline(String description) {
        super(description);

    }

    /**
     * initialises an task object with the given description and deadline
     * 
     * @param description description of task
     * @param deadline deadline of deadline
     */

    public Deadline(String description, LocalDate deadline) {
        super(description, deadline);

    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    @Override
    public String getTaskFileSaveFormat() {
        return "[D]###" + super.getTaskFileSaveFormat();
    }
}
