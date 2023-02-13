package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to add new Event Task to TaskList.
 *
 * @author Lian Kok Hai
 */
public class EventCommand extends Command {
    protected String taskName;
    protected String from;
    protected String to;

    /**
     * Constructs new EventCommand.
     *
     * @param taskName Name of Event.
     * @param from Date of beginning of event.
     * @param to Date of end of event.
     */
    public EventCommand(String taskName, String from, String to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Event(taskName, from, to);
        taskList.addTask(newTask);
        storage.saveTaskList(taskList);
        return ui.getAddTaskMessage(newTask, taskList.getCount());
    }
}
