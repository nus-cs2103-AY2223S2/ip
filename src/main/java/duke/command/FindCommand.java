package duke.command;

import duke.tasklist.TaskList;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.findKeywordInTasks(keyword);
    }
}
