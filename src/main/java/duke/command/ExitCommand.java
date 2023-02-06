package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

public class ExitCommand extends Command{
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        exit = true;
        String output;
        try {
            storage.store(tasks);
            output = ui.showStored(tasks);
        } catch (IOException ignored) {
            output = ui.showLoadingError();
        }
        output += ui.showGoodbye();
        return output;
    }
}
