package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

/**
 * A command printing the exit text. Does not directly cause the program to exit.
 */
public class ExitPrint implements Command {
    /**
     * Creates an exit-print command.
     */
    public ExitPrint() {

    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Otsumiki!~ I'll see you later!");
    }
}
