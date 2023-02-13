package duke.Note;

/**
 * The abstraction behind each task stored by the duke.Utilities.Duke note list.
 */
public class Note {

    private String content;

    /**
     * The constructor for a Note object.
     * @param content Content of the note.
     */
    public Note(String content) {
        this.content = content;
    }

    /**
     * Create a new note that is loaded with data that comes from a notes list.
     * @param data Data to load in the note object.
     * @return Note object.
     */
    public static Note dataToNote(String data) {
        return new Note(data);
    }

    @Override
    public String toString() {
        return this.content;
    }
}
