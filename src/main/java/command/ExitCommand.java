package command;

import dukeexception.storageexception.SaveFileException;
import gui.Gui;
import storage.Storage;
import task.TaskList;

/**
 * Command for ending the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        gui.sayFarewell();
        try {
            storage.save(taskList);
        } catch (SaveFileException e) {
            gui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
