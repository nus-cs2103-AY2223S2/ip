package kira.command;

import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Command for LIST.
 */
public class ListCommand extends Command {

    /**
     * Constructs an executable to list out all tasks
     * in the list.
     */
    public ListCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) {
        ui.listMsg(taskList.getList());
        return true;
    }

}
