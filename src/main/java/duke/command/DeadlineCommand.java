package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.ui.Ui;
import duke.exception.DukeException;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Deadline(taskName, by);
        taskList.addTask(newTask);
        ui.printAddTaskMessage(newTask, taskList.getCount());
        storage.saveTaskList(taskList);
    }
}
