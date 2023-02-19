package duke.command;

import duke.Command;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.ShowList(tasks.getAllTasks());
    }
}