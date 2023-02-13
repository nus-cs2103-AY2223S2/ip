package duke.Exception;

/**
 * An InvalidNoteException a type of DukeException that is thrown when a certain valid command based on a note
 * number (delete) takes in a task number of a task which does not yet exist/cannot exist.
 */
public class InvalidNoteException extends DukeException {

    /**
     * The constructor for an InvalidNoteException.
     * @param noteNumber The (invalid) note number input by the user.
     */
    public InvalidNoteException(int noteNumber) {
        super("Sorry, the note number " + noteNumber + " has not been added/cannot be added! Please try again");
    }

    @Override
    public String getExceptionType() {
        return "Invalid note number";
    }
}
