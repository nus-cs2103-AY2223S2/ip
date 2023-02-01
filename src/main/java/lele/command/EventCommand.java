package lele.command;

import lele.storage.Storage;
import lele.task.Event;
import lele.task.TaskList;
import lele.ui.Ui;

import java.io.IOException;

/**
 * Handles actions taken when user requests
 * an event to be created.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Instantiates the event instance passed from another class.
     * @param event Event instance.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Adds task to task list, updates the storage
     * and finally prints the task added to command
     * line.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTasks(event);
            storage.updateStorage(taskList);
            ui.printAddTask(taskList, event);
        } catch  (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adding an event task does not terminate program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
