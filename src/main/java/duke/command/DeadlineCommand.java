package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.ui.Ui;
import duke.exception.DukeException;

public class DeadlineCommand extends Command {
    protected String taskName;
    protected String by;

    public DeadlineCommand(String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Deadline(taskName, by);
        ui.print(taskList.addTask(newTask));
        storage.saveTaskList(taskList);
    }
}
