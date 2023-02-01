package duke;

import java.io.IOException;
import java.util.List;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        Task taskToDelete = taskList.get(taskNumber - 1);
        taskList.remove(taskToDelete);
        storage.save(taskList);
        ui.showDeleteTask(taskToDelete, taskList);
    }
}
