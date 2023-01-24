package duke;

import duke.Task;

public class ToDo extends Task {

    protected String by;

    public ToDo(String description) {
        super(description);
        this.description = description;
        Task.actions += 1;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
    @Override
    public String toSaveString() {
        return String.format("todo || %s || %s", super.toSaveString(), this.description);
    }
}

