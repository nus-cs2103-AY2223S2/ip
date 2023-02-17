package commands;

import java.io.IOException;

import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;


/**
 * Represents command for exiting the program
 */
public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Execute exit command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.bye();
        storage.bye();
    }

    public String generate(TaskList tasks, Ui ui, Storage storage) {
        return ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
