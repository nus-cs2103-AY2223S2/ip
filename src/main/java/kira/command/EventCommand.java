package kira.command;

import kira.storage.TaskList;
import kira.task.Event;
import kira.ui.Ui;

/**
 * Command for EVENT.
 */
public class EventCommand extends Command {

    private Event task;

    /**
     * Constructs an executable to store an event task.
     *
     * @param task event task to be stored
     */
    public EventCommand(Event task) {
        this.task = task;
    }

    @Override
    public boolean execute(Ui ui, TaskList taskList) {
        taskList.store(task);
        ui.storeTaskMsg(task, taskList.getTotal());
        return true;
    }

}
