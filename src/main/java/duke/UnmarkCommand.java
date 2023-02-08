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
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        Task currTask = taskList.get(taskNumber - 1);

        currTask.setDone(false);
        storage.save(taskList);
        return response.showTaskMarkUndone(currTask);
    }
}
