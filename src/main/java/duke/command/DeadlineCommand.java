package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Deadline(taskName, by);
        taskList.addTask(newTask);
        storage.saveTaskList(taskList);
        return ui.getAddTaskMessage(newTask, taskList.getCount());
    }
}
