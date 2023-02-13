package duke.Command;

import duke.Exception.InvalidNoteException;
import duke.Utilities.NoteList;
import duke.Utilities.Storage;
import duke.Utilities.TaskList;
import duke.Utilities.UI;

/**
 * The delete note command which is executed by Duke.
 */
public class DeleteNoteCommand extends Command {

    private int index;

    /**
     * The constructor for a new delete note command.
     * @param index Index of the note to be deleted.
     */
    public DeleteNoteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, NoteList notes, UI ui, Storage storage) throws InvalidNoteException {
        if (index < 1 || index > notes.getItems()) {
            throw new InvalidNoteException(index);
        }
        String confirmationMessage = notes.deleteNote(index -1);
        storage.saveToFile(tasks.getTasks(), notes.getNotes());
        return confirmationMessage;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
