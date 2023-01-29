package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class to represent a Task.
 * Has a taskName and completionStatus, which all tasks have.
 */
public abstract class Task {

    protected final String taskName;
    private boolean completionStatus;

    /**
     * Abstract constructor for Task to set taskName.
     * CompletionStatus is false by default
     *
     * @param taskName Name of this task
     */
    public Task(String taskName) {
        this.completionStatus = false;
        this.taskName = taskName;
    }

    /**
     * Toggles the completion status of this task
     * converts to completed task if task was not completed
     * converts to uncompleted task if task was completed
     */
    public void toggleState() {
        completionStatus = !completionStatus;
    }

    /**
     * Returns the completion status of this task
     *
     * @return the completion status of this task
     */
    public boolean getCompletionStatus() {
        return completionStatus;
    }

    /**
     * Loads the completion status from save file representation
     *
     * @param inp Loads the completion status of this task from save file representation
     */
    public void loadCompletionStatus(String inp) {
        completionStatus = (inp == "1");
    }

    /**
     * Abstract method to convert this task to save file representation
     *
     * @return Converts this Task to save file representation
     */
    public abstract String toSaveData();

    /**
     * Converts this task into a string for user
     *
     * @return Converts this Task into a string for user
     */
    @Override
    public String toString() {
        return "[" + (completionStatus ? "X" : " ") + "] " + taskName;
    }

    protected String displayLocalDate(LocalDateTime input) {
        return input.format(DateTimeFormatter.ofPattern("E h:mma', 'MMM d yyyy"));
    }

}
