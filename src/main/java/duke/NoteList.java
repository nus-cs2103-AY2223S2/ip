package duke;

import java.util.ArrayList;

/** A class NoteList that contains the list of notes e.g., it has operations to add/delete notes in the list */
public class NoteList {
    /** The array that stores the list */
    private static ArrayList<Note> notes;

    /**
     * Initializes an TaskList object with the given array.
     *
     * @param notes The given array that contains tasks
     */
    public NoteList(ArrayList<Note> notes){
        this.notes = notes;
    }

    /**
     * Return the list of all notes
     *
     * @return List of all notes
     */
    public static ArrayList<Note> getList() {
        return notes;
    }

    /**
     * Add the task into the array
     *
     * @param note The task
     */
    public static void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Remove the task with the number of task given
     *
     * @param index The number of task given
     * @return The task removed
     */
    public static Note remove(int index) {
        return notes.remove(index);
    }

    /**
     * Destroy the NoteList
     */
    public static void close() {
        notes = null;
    }
}
