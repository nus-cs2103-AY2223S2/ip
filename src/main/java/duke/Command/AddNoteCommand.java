package duke.Command;

import duke.Exception.InvalidTaskException;
import duke.Note.Note;
import duke.Utilities.NoteList;
import duke.Utilities.Storage;
import duke.Utilities.TaskList;
import duke.Utilities.UI;

/**
 * The add note command which is executed by Duke.
 */
public class AddNoteCommand extends Command {

    private String data;

    /**
     * Constructor for a new add note command.
     * @param data Data to be stored in note.
     */
    public AddNoteCommand(String data) {
        this.data = data;
    }

    @Override
    public String execute(TaskList tasks, NoteList notes, UI ui, Storage storage) {
        Note note = new Note(data);
        String confirmationMessage = notes.addNote(note);
        storage.saveToFile(tasks.getTasks(), notes.getNotes());
        return confirmationMessage;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
