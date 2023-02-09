package duke.command;

import duke.Storage;
import duke.exception.FileException;
import duke.task.TaskList;
import duke.Ui;


public class ExitCommand extends Command{
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        exit = true;
        String output;
        try {
            storage.store(tasks);
            output = ui.showStored(storage, tasks);
        } catch (FileException ignored) {
            output = ui.showLoadingError();
        }
        output += ui.showGoodbye();
        return output;
    }
}
