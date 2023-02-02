package duke.task;

import duke.exception.InvalidFormatException;

public class Todo extends Task {

    public static InvalidFormatException getInvalidFormatException() {
        return new InvalidFormatException("todo name");
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
        classIcon = "T";
    }

    public Todo(String description) {
        this(description, false);
    }
}
