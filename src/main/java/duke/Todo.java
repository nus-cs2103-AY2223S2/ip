package duke;

import java.time.LocalDate;

/**
 * Represents a task of todo type
 */
public class Todo extends Task {

    /**
     * initialises an todo object with the given description
     * 
     * @param description
     *        description of todo
     */

    public Todo(String description) {
        super(description);

    }

    /**
     * initialises an todo object with the given description and deadline
     * 
     * @param description
     *        description of todo
     * @param deadline
     *        deadline of todo
     */

    public Todo(String description, LocalDate deadline) {
        super(description, deadline);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskFileSaveFormat() {
        return "[T]###" + super.getTaskFileSaveFormat();
    }

}