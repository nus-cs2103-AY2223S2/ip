package duke.model.command;

import java.util.Objects;
import java.util.stream.Collectors;

import duke.model.task.Task;
import duke.model.task.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList list) {
        String content = list.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
        return "Here are the matching tasks in your list:\n" + content;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FindCommand)) {
            return false;
        }
        FindCommand cmd = (FindCommand) obj;
        return Objects.equals(keyword, cmd.keyword);
    }
}
