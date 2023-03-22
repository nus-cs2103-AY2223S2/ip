package duke.commands;

import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

public class UndoCommand extends Command {
    private final int index;
    private final String filePath;
    public UndoCommand(int index, String filePath) {
        this.index = index;
        this.filePath = filePath;
    }
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Storage store = new Storage(this.filePath);

        tasks.replace(store.loadFile(this.index));
        store.deleteBefore(this.index);
        if (index != 1) {
            return "Okay, I have undone what you did by " + index + " steps. Boop beep.";
        } else {
            return "Okay, I have undone what you did. Beep boop.";
        }

    }

}
