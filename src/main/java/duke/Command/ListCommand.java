package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

public class ListCommand extends Command{
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        ui.listAll(tasks);
        return "";
    }
}
