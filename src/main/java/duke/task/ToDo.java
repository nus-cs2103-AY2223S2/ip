package duke.task;

/**
 * Represents a ToDo object.
 */
public class ToDo extends Task {
    /**
     * Constructs an unchecked ToDo with a title and deadline.
     * @param title Title of ToDo task.
     */
    public ToDo(String title) {
        super(title);
    }

    /**
     * Constructs a ToDo with a title, and is checked or unchecked depending on the value of isDone.
     * @param title Title of ToDo task.
     * @param isDone Boolean to indicate if the Deadline should be checked or not.
     */
    public ToDo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Returns the string format to be saved on disk.
     * @return ToDo object in disk format.
     */
    @Override
    public String toDiskFormat() {
        return String.format("T|%d|%s", super.getIsDone() ? 1 : 0, super.getTitle());
    }

    /**
     * Returns a printable version of the ToDo object. It contains the title,
     * and an indicator of whether it is checked or not.
     * @return The string representation of this ToDo object.
     */
    @Override
    public String toString() {
        return String.format("[%s][T] %s", super.getIsDone() ? "X" : " ", super.getTitle());
    }
}
