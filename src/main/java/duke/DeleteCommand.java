package duke;

import java.io.IOException;
import java.util.List;

public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for a Delete command.
     *
     * @param taskNumber Task number of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        Task taskToDelete = taskList.get(taskNumber - 1);
        taskList.remove(taskToDelete);
        storage.save(taskList);
        return response.showDeleteTask(taskToDelete, taskList);
    }
}
