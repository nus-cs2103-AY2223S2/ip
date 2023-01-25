package duke.command;

import duke.storage.StorageList;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.printList();
        return true;
    }
}
