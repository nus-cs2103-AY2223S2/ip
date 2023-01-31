package kira.command;

import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Command for TODAY.
 */
public class TodayCommand extends Command {

    /**
     * Constructs an executable to list tasks ongoing
     * today.
     */
    public TodayCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) {
        ui.todayMsg(taskList.findToday());
        return true;
    }

}
