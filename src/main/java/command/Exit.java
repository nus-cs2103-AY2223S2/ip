package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * A command printing the exit text, and invoking the UI to close if necessary.
 */
public class Exit implements Command {
    /**
     * Creates an exit command.
     */
    public Exit() {

    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printMiki("Otsumiki!~ I'll see you later!");
        ui.close();
    }
}
