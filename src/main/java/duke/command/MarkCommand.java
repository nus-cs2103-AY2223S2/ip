package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.UI.UI;

/**
 * The mark command.
 * Extends from Command.
 * Marks a task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND = "mark";
    private String[] index;

    /**
     * The constructor for the mark command.
     * @param index The arguments of the command.
     */
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
