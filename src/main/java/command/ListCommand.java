package command;

import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        tasklist.printList();
    }
}
