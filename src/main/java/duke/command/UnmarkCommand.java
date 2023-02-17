package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.UI.UI;

public class UnmarkCommand extends Command {
    public static final String COMMAND = "unmark";
    private String[] index;

    public UnmarkCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        int taskNumber = Character.getNumericValue(index[1].charAt(0));
        taskList.unmark(taskNumber, storage);
        ui.showUnmark(taskList.getTask(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}