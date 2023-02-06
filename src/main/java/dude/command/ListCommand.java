package dude.command;

import dude.storage.Storage;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to list all the Tasks in TaskList.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
