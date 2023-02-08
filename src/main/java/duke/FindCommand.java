package duke;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) {
        List<Task> taskList = tasks.getTasks();

        return response.showFoundTasks(taskList.stream().filter(task -> task.toString().contains(keyword))
                .collect(Collectors.toList()));
    }
}
