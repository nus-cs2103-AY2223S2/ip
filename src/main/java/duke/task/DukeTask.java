package duke.task;

import java.util.regex.Pattern;

/**
 * An abstract Task class encapsulating a task in Duke, which can be extended
 * by more specific tasks like Events, toDos, etc.
 */

public abstract class DukeTask {
    private final String information;
    private final TaskType type;
    private boolean isDone;

    public DukeTask(String info, TaskType type) {
        this.information = info;
        this.type = type;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getInformation() {
        return this.information;
    }

    public TaskType getType() {
        return this.type;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public abstract String storageString();

    /**
     * Takes in a description and check whether the current task information that matches the given description.
     *
     * @param description The given description to be checked
     * @return Whether the description and the information matches
     */
     public boolean matches(String description) {
         return this.information.toUpperCase().contains(description.toUpperCase());
     }

    @Override
    public String toString() {
        if (getStatus()) {
            return "[X] " + this.information;
        } else {
            return "[ ] " + this.information;
        }
    }
}
