package duke.task;

import duke.task.Task;

/**
 * ToDo is a subclass of Task. It is the most general as it only
 * contains the description of the task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo
     * @param description Details of the task
     * @param isDone Keeps track of whether the task is completed
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Same as above constructor, but with isDone initialised as false
     */
    public ToDo(String description) {
        super(description);
    }

    public String getTaskIcon() {
        return "T";
    }
    /**
     * @return String with task formatted to be saved into duke.txt
     */
    public String toSave() {
        return this.getTaskIcon() + SEPARATOR + convertBoolean()
                + SEPARATOR + this.getDescription();
    }

    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.getTaskIcon(), this.getStatusIcon(),
                this.getDescription());
    }
}
