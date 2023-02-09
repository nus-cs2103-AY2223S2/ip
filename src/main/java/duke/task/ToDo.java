package duke.task;

import duke.TaskType;

/**
 * Tasks without deadlines or start and end times.
 */
public class ToDo extends Task {
    private String description;

    private ToDo(String description) {
        super(TaskType.TODO, description);
        this.description = description;
    }

    /**
     * Factory method of ToDo objects.
     * @param str String containing description of task that needs to be done.
     * @return ToDo object.
     */
    public static ToDo to(String str) {
        return new ToDo(str);
    }

    /**
     * Creates a string representing the ToDo object so that it can be saved by Storage object.
     * @return String representing the ToDo object.
     */
    @Override
    public String taskToSavedForm() {
        String marked = super.getStatusIcon() == "X" ? "1" : "0";
        return "todo " + this.description + marked;
    }

    /**
     * Update /by field.
     * @param date The update.
     */
    @Override
    public void updateByField(String date) {
        throw new RuntimeException("Unable to update! Task not a deadline!");
    }

    /**
     * Update /from field.
     * @param date The update.
     */
    @Override
    public void updateFromField(String date) {
        throw new RuntimeException("Unable to update! Task not an event!");
    }

    /**
     * Update /to field.
     * @param date The update.
     */
    @Override
    public void updateToField(String date) {
        throw new RuntimeException("Unable to update! Task not an event!");
    }

    /**
     * Creates custom string containing ToDo object's description.
     * @return String representing ToDo object.
     */
    @Override
    public String toString() {
        String str = super.toString();
        return str;
    }
}
