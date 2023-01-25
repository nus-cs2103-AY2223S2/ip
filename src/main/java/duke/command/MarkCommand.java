package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private int listNum;

    public MarkCommand(String input) {
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.markTask(listNum);
        return true;
    }
}