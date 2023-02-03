package duke.tasks;

/**
 *  A task representing an to do.
 */
public class ToDo extends Task {
    private static final String tag = "T";

    /**
     *  Constructor for to do event with description
     *
     * @param description description of the to do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the to do in a formatted string to be saved locally.
     *
     * @return the to do in string format
     */
    public String saveTask() {
        String completed = this.isDone ? "1" : "0";
        return this.tag + " | " + completed + " | "
                + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
