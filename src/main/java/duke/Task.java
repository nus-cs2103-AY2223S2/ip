package duke;
/**
 * Encapsulates a task
 */
class Task {
    /**
     * Status of the task.
     */
    boolean completed ;

    /**
     * Details of the task.
     */
    String description;

    /**
     * Data of the task stored in the hard drive.
     */
    String storedData;
    

    public Task(String keyword, String description, Boolean completed) {
        this.description = description;
        this.storedData = keyword + " " + description;
        this.completed = completed;
    }

    /**
     * Provides Details of the description.
     * @return String detail message of description.
     */
    public String provideDetails() {
        return completed ? "[x] " + description
                : "[ ] " + description;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Marks the task as completed/
     */
    public void mark() {
        this.completed = true;
    }


    /**
     * Gets the data that describes the task which will be stored in the hard drive.
     * @return A String that describes how the task is stored in the hard drive.
     */
    public String getStorageLine() {
        return this.completed ? "1 " + storedData
                              : "0 " + storedData;
    }

}
