package duke.command;

/**
 * The command to find tasks in the task list.
 */
public class FindTasksCommand extends Command {
    private String keyword;

    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        ui.printFoundTasks(taskList.findTasks(keyword));
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
