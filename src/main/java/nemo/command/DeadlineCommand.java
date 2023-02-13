package nemo.command;

import nemo.exception.NemoException;
import nemo.storage.Storage;
import nemo.task.Deadline;
import nemo.task.Task;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to add new Deadline Task to TaskList.
 *
 * @author Lian Kok Hai
 */
public class DeadlineCommand extends Command {
    protected String taskName;
    protected String by;

    /**
     * Constructs new DeadlineCommand.
     *
     * @param taskName Name of Deadline Task.
     * @param by Due date of deadline task.
     */
    public DeadlineCommand(String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NemoException {
        Task newTask = new Deadline(taskName, by);
        taskList.addTask(newTask);
        storage.saveTaskList(taskList);
        return ui.getAddTaskMessage(newTask, taskList.getCount());
    }
}
