package duke.task;

/**
 * A thing to do.
 */
public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the ToDo details to save in data/tasks.txt.
     * @return ToDo details.
     */
    public String getDetailsToSave() {
        return String.format("todo %s\n%s", isDone, desc);
    }
}
