package duke.command;

import duke.exception.DukeException;
import duke.storage.Note;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Class for NoteCommand
 */
public class NoteCommand extends Command {
    /**
     * Constructor for TodoCommand.
     * @param userInput User input.
     */
    public NoteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Extracts note from user input.
     * @param userInput User input.
     * @return Note inputted by user.
     */
    public String getNote(String userInput) throws DukeException {
        String note = "";
        try {
            note = userInput.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Oops! Please enter a note.");
        }
        if (note.isBlank()) {
            throw new DukeException("Oops! Please enter a note.");
        }
        return note;
    }

    /**
     * Executes user input and adds note.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return Message to inform user that note has been added.
     */
    @Override
    public String execute(TaskList tasks, Note notes) throws DukeException {
        notes.addNote(getNote(userInput));
        Storage.saveNotesToFile(notes);
        return "The following note has been added:\n"
                + getNote(userInput);
    }
}
