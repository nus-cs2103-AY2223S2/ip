package duke.storage;

/**
 * Class for Storage.
 */
public class Note {
    private String notes;

    public Note(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }

    /**
     * Adds new note.
     * @param newNote New note.
     */
    public void addNote(String newNote) {
        notes += (newNote + "\n");
    }

    public void deleteAll() {
        this.notes = "";
    }
}
