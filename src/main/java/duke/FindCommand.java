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
        List<Task> filteredList = tasks.filter(keyword);

        return response.showFoundTasks(filteredList);
    }
}
