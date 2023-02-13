package duke.Command;

import duke.Exception.InvalidArgumentsException;
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
     * The constructor for a deletenote command object.
     * @param index User input after the deletenote command. If it is not in proper format, throw exception.
     * @throws InvalidArgumentsException Thrown if the arguments are not in the proper format.
     */
    public DeleteNoteCommand(String index) throws InvalidArgumentsException {
        try {
            int numToDelete = Integer.parseInt(index);
            this.index = numToDelete;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException();
        }
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
