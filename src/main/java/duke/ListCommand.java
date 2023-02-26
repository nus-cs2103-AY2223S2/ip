package duke;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Response response, Storage storage) {
        return response.showTaskList(tasks.getTasks());
    }
}
