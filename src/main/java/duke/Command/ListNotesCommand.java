package duke.Command;

import duke.Utilities.NoteList;
import duke.Utilities.Storage;
import duke.Utilities.TaskList;
import duke.Utilities.UI;

/**
 * The list notes command which is executed by Duke.
 */
public class ListNotesCommand extends Command {

    @Override
    public String execute(TaskList tasks, NoteList notes, UI ui, Storage storage) {
        return notes.printNoteList();
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
