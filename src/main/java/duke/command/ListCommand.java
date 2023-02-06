package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command{
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showAll(tasks);
        return "";
    }
}
