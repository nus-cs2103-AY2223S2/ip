package Command;

import Storage.TaskList;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        if (tasks.numOfTask() == 0) {
            return "List is empty";
        }
        return tasks.getTaskList();
    }
}
