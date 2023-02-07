package duke.command;

import java.util.Objects;

import duke.TaskList;

public class FindTaskCommand extends Command {

    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    String tryExecute(TaskList list) {
        return "Here are the matching tasks in your list:\n"
                + list.listTasksContainKeyword(keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FindTaskCommand)) {
            return false;
        }
        FindTaskCommand cmd = (FindTaskCommand) obj;
        return Objects.equals(keyword, cmd.keyword);
    }
}
