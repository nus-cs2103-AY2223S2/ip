package duke.tasks;

/**
 * ToDo Task class.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toText() {
        int done;
        if (this.isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "T" + " | " + done + " | " + this.description;
    }
}
