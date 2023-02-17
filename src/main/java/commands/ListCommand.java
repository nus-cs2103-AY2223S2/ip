package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents command for printing the current list of tasks in the TaskList
 */
public class ListCommand extends Command{
    public ListCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.print();
    }
}
