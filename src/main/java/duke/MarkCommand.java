package duke;

import java.io.IOException;
import java.util.List;

public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for a Mark command.
     *
     * @param taskNumber Task number of the task in the task list to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        Task currTask = taskList.get(taskNumber - 1);

        currTask.setDone(true);
        storage.save(taskList);
        return response.showTaskMarkDone(currTask);
    }
}
