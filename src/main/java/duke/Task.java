package duke;
/**
 * Encapsulates a description.
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
     * Flips the completed of the description.
     */
    public void unmark() {
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }


    public String getStorageLine() {
        return this.completed ? "1 " + storedData
                              : "0 " + storedData;
    }

}
