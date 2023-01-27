package iris.command;

import iris.TaskList;
import iris.Ui;
import iris.TaskStore;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        Ui.output(tasks.list());
    }
}
