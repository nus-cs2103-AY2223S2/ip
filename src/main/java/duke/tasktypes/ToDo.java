package duke.tasktypes;

import java.time.LocalDateTime;

/**
 * Represents a Todo Task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.when = LocalDateTime.MAX;
    }

    @Override
    public String getSaveFormat() {
        String done;
        if (this.isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return "T" + ",," + done + ",," + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

