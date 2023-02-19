package duke.command;

/**
 * Finds tasks in the task list.
 */
public class FindTasksCommand extends Command {
    private String keyword;

    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        return ui.printFoundTasks(taskList.findTasks(keyword));
    }

    @Override
    public String toString() {
        return String.format("FindTasksCommand{keyword=%s}", keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FindTasksCommand) {
            return keyword.equals(((FindTasksCommand) obj).keyword);
        }
        return false;
    }

}
