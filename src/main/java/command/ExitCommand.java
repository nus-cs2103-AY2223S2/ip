package command;

import dukeexception.storageexception.SaveFileException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command for ending the chatbot program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
        try {
            storage.save(taskList);
        } catch (SaveFileException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
