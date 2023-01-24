package duke.command;

import java.util.Objects;

import duke.TaskList;
import duke.UI;

public class FindTaskCommand extends Command {

    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList list) {
        String msg = "Here are the matching tasks in your list:\n"
                + list.listTasksContainKeyword(keyword);
        UI.echo(msg);
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
