package jeno.command;

import jeno.exception.JenoException;
import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;

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
    public String execute(TaskList tasks, Note notes) throws JenoException {
        notes.deleteAll();
        Storage.deleteNotes();
        return "Got it! Successfully cleared all notes.";
    }
}
