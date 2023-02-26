package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MarkCommand extends Command {
    private int taskNumber;
    private LocalDateTime markDate;

    /**
     * Constructor for a Mark command.
     *
     * @param taskNumber Task number of the task in the task list to be marked as done.
     * @param markDate Date the task was marked as done.
     */
    public MarkCommand(int taskNumber, LocalDateTime markDate) {
        this.taskNumber = taskNumber;
        this.markDate = markDate;
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        Task currTask = taskList.get(taskNumber - 1);

        currTask.setDone(true, markDate);
        storage.save(taskList);
        return response.showTaskMarkDone(currTask);
    }
}
