package jeno.command;

import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;

/**
 * Class for ClearNotesCommand
 */
public class ClearNotesCommand extends Command {
    /**
     * Constructor for Command class.
     *
     * @param userInput
     */
    public ClearNotesCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes user input and clears all notes.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return Message to inform user that notes have been cleared.
     */
    @Override
    public String execute(TaskList tasks, Note notes) {
        notes.deleteAll();
        Storage.deleteNotes();
        return "Got it! Successfully cleared all notes.";
    }
}
