package duke.commands;

import duke.tasklist.TaskList;

/**
 * Command to search for keywords in task of a task list.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super("FIND");
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks) {
        assert this.keyword != "";
        return tasks.find(this.keyword);
    }
}

