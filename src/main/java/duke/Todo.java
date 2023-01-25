package duke;

import java.time.LocalDate;

public class Todo extends Task {
    public Todo(String description) {
        super(description);

    }

    public Todo(String description, LocalDate deadline) {
        super(description, deadline);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}