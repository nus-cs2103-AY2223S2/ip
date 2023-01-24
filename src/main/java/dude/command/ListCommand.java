package dude.command;

import dude.task.TaskList;
import dude.storage.Storage;
import dude.ui.Ui;

public class ListCommand extends Command{
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
