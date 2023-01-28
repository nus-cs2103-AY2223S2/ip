package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        Ui.output(tasks.list());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
