package duke;

import java.io.IOException;
import java.util.List;

public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for an Unmark command.
     *
     * @param taskNumber Task number of the task in the task list to be marked as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        Task currTask = taskList.get(taskNumber - 1);

        currTask.markAsNotDone();;
        storage.save(taskList);
        ui.showTaskMarkUndone(currTask);
    }
}
