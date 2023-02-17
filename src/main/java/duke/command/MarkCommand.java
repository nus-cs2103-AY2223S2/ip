package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.UI.UI;

public class MarkCommand extends Command {
    public static final String COMMAND = "mark";
    private String[] index;

    public MarkCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        int taskNumber = Character.getNumericValue(index[1].charAt(0));
        taskList.mark(taskNumber, storage);
        ui.showMark(taskList.getTask(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
