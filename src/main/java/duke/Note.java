package duke;

public class Note {
    /** Name of the task */
    private String note;

    /**
     * Initializes an Note object with the given values.
     *
     * @param note The name of the note
     * @return A note instance
     */
    public Note(String note) {
        this.note = note;
    }


    /**
     * Get the name of the task
     *
     * @return A string representation of the task's name
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Returns the string representation of the task, including
     * whether the task is done or not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return this.note;
    }
}
