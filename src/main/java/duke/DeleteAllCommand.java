package duke;

import java.io.IOException;
import java.util.List;

public class DeleteAllCommand extends Command {
    private String keyword;

    /**
     * Constructor for a Delete All command.
     *
     * @param keyword Keyword used to find tasks to delete from task list.
     */
    public DeleteAllCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        List<Task> tasksToRemove = tasks.filter(keyword);

        if (tasksToRemove.isEmpty()) {
            return response.showNoTasksFound(keyword);
        }

        for (Task t : tasksToRemove) {
            taskList.remove(t);
        }
        storage.save(taskList);

        return response.showDeleteAllTasks(tasksToRemove, taskList, keyword);
    }
}
