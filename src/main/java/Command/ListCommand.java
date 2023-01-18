package Command;

import Storage.TaskList;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return tasks.getTaskList();
    }
}
