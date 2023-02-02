package duke;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();

        ui.showFoundTasks(taskList.stream().filter(task -> task.toString().contains(keyword))
                .collect(Collectors.toList()));
    }
}
