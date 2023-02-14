package duke.tasks;
/**
 * Encapsulates a task
 */
public class Task {
    /**
     * Status of the task.
     */
    boolean isComplete ;

    /**
     * Details of the task.
     */
    String taskDescription;

    /**
     * Data of the task stored in the hard drive.
     */
    String storedData;

    public Task(String keyword, String taskDescription, Boolean isComplete) {
        this.taskDescription = taskDescription;
        this.storedData = keyword + " " + taskDescription;
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return isComplete ? "[x] " + taskDescription
                          : "[ ] " + taskDescription;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void unmark() {
        this.isComplete = false;
    }

    /**
     * Marks the task as completed/
     */
    public void mark() {
        this.isComplete = true;
    }


    /**
     * Gets the data that describes the task which will be stored in the hard drive.
     * @return A String that describes how the task is stored in the hard drive.
     */
    public String getStorageLine() {
        return this.isComplete ? "1 " + storedData
                              : "0 " + storedData;
    }

}
