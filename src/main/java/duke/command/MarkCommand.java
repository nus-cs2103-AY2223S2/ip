package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Mark Command to make any task on the list to be done.
 */
public class MarkCommand extends Command {
    private int listNum;

    public MarkCommand(String input) {
        int tasknumbermark = Integer.valueOf(input) - 1;
        this.listNum = tasknumbermark;
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.markTask(listNum);
        return true;
    }
}