package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui) {
        String output = "";
        output += ui.printListMessage();
        output += list.list();
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
