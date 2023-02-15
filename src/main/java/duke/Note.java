package duke;

/**
 * A class that represents a Note
 */
public class Note {
    /** Content of the note */
    private String note;

    /**
     * Initializes a Note object with the given values.
     *
     * @param note The content of the note
     * @return A note instance
     */
    public Note(String note) {
        this.note = note;
    }

    /**
     * Returns the string representation of the note
     *
     * @return The string representation of the note.
     */
    @Override
    public String toString() {
        return this.note;
    }
}
