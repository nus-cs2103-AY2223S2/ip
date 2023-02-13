package duke.Utilities;

import duke.Note.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class that stores the notes that users input.
 */
public class NoteList {
    private List<Note> notes;
    protected int items;

    /**
     * Constructor to create a new NoteList object from scratch
     */
    public NoteList() {
        this.notes = new ArrayList<>();
    }

    /**
     * Constructor to create a new NoteList object from an existing list.
     * @param notes Existing list of notes.
     */
    public NoteList(List<Note> notes) {
        this.notes = notes;
        this.items = notes.size();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public int getItems() {
        return items;
    }

    /**
     * Add a note to the list of notes.
     * @param note Note to be added.
     * @return Confirmation message that the note is added.
     */
    public String addNote(Note note) {
        notes.add(note);
        items++;
        return "Got it. I've added this note:\n"
                + String.format(" %s\n Now you have %s notes in the list.", note.toString(), items);
    }

    /**
     * Delete a note from the existing list of notes.
     * @param noteNumber Note number of the note to be deleted.
     * @return Confirmation message that the node is deleted.
     */
    public String deleteNote(int noteNumber) {
        Note removedNote = notes.get(noteNumber);
        notes.remove(noteNumber);
        items--;
        return "Got it. I've removed this note:\n"
                + String.format(" %s\n Now you have %s notes in the list.",
                  removedNote, items);
    }

    public String printNoteList() {
        StringBuilder notelist = new StringBuilder();
        notelist.append("Here are the notes in your list:" + "\n");

        for (int i = 0; i < items; i++) {
            String noteToAdd = String.format("%s. %s\n", i + 1, notes.get(i));
            notelist.append(noteToAdd);
        }

        return notelist.toString();
    }
}
