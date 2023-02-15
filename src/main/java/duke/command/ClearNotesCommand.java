package duke.command;

import duke.exception.DukeException;
import duke.storage.Note;
import duke.storage.Storage;
import duke.storage.TaskList;

public class ClearNotesCommand extends Command {
    /**
     * Constructor for Command class.
     *
     * @param userInput
     */
    public ClearNotesCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks, Note notes) throws DukeException {
        notes.deleteAll();
        Storage.deleteNotes();
        return "Got it! Successfully cleared all notes.";
    }
}
