package Command;

import Storage.TaskList;
import Task.Task;

public class RemoveCommand extends Command {
    private String request;

    public RemoveCommand(String request) {
        this.request = request;
    }

    @Override
    public String execute(TaskList tasks) {
        String[] req = request.trim().split("delete ");
        Integer idx = Integer.parseInt(req[1]) - 1;
        String deleted_task = tasks.getTask(idx).toString();
        tasks.deleteTask(idx);
        return "Noted. I've removed this task:\n" + deleted_task +
                "\nNow you have "+ tasks.numOfTask() + " in the list.";
    }
}
