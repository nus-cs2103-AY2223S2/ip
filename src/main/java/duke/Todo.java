package duke;

import java.time.LocalDate;


/**
 * A todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with given description.
     *
     * @param description Description of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
