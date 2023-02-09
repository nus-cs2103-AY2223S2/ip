package alfred.command;

import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents a list command when a user wishes to list all the tasks.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder command = new StringBuilder("Here are your pending tasks: \n");
        if (tasks.isEmpty()) {
            return ui.getCommandMessage("Woohoo! You have no pending tasks\n");
        }
        assert tasks.getSize() > 0 : "Tasks should not be empty";

        String itemList = tasks.getList();
        return ui.getCommandMessage(command.append(itemList).toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
