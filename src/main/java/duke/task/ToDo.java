package duke.task;

import java.util.ArrayList;

/**
 * A task subclass to represent a ToDo (no date).
 */
public class ToDo extends Task {

    /**
     * Constructor for Task class.
     * @param description The task details.
     * @param tags Tags for the task.
     */
    public ToDo(String description, ArrayList<String> tags) {
        super(description, tags);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ToDo) {
            ToDo other = (ToDo) obj;
            return this.description.equals(other.description);
        }
        return false;
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
