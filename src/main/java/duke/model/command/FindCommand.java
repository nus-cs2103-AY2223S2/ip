package duke.model.command;

import java.util.Objects;

import duke.model.task.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList list) {
        return "Here are the matching tasks in your list:\n"
                + list.listTasksContainKeyword(keyword);
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
