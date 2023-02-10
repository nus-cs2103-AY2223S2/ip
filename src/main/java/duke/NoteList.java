package duke;

import java.util.ArrayList;

public class NoteList {
    /** The array that stores the list */
    private static ArrayList<Note> notes;

    /**
     * Initializes an TaskList object.
     *
     */
    public NoteList() {
        notes = new ArrayList<>();
    }

    /**
     * Initializes an TaskList object with the given array.
     *
     * @param tasks The given array that contains tasks
     */
    public NoteList(ArrayList<Note> notes){
        this.notes = notes;
    }

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
     * Destroy the TaskList
     */
    public static void close() {
        notes = null;
    }
}
