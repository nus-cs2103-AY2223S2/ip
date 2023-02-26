package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class UnmarkCommand extends Command {
    private int taskNumber;
    private LocalDateTime unmarkDate;

    /**
     * Constructor for an Unmark command.
     *
     * @param taskNumber Task number of the task in the task list to be marked as not done.
     * @param unmarkDate Date task was marked as not done.
     */
    public UnmarkCommand(int taskNumber, LocalDateTime unmarkDate) {
        this.taskNumber = taskNumber;
        this.unmarkDate = unmarkDate;
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        Task currTask = taskList.get(taskNumber - 1);

        currTask.setDone(false, unmarkDate);
        storage.save(taskList);
        return response.showTaskMarkUndone(currTask);
    }
}
