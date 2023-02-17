package duke.task;

/**
 * Represents the simplest type of task.
 */
public class ToDo extends Task {
    /**
     * Constructor Method for ToDo.
     *
     * @param title title of the ToDo.
     */
    public ToDo(String title) {
        super(title);
        this.type = "[T]";
    }

    /**
     * Overloaded Constructor for when loading from hard drive.
     *
     * @param title title of the ToDo.
     * @param done boolean to represent if task is done or not;
     */
    public ToDo(String title, boolean done) {
        super(title);
        this.type = "[T]";
        this.done = done;
    }

    /**
     * Alternative toString method purely for writing to hard drive.
     * Can also be used for testing.
     *
     * @return String representation of the task with all attribute information.
     */
    @Override
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title;
    }

}
