package Duke.command;

import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        terminate = true;
        String output;
        storage.store(tasks);
        output = ui.printStoredTasks(tasks);
        output += ui.bye();
        return output;
    }
}
