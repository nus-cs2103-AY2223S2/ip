package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;

/**
 * finds tasks corresponding to given keyword
 */
public class FindCommand extends Command {
    private TaskList filteredTasks;
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, TaskStore taskStore) throws IrisException {
        this.filteredTasks = tasks.findTask(this.keyword);
        assert filteredTasks.size() < tasks.size() : "filtered tasks should be less than total tasks";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) {
        if (filteredTasks.size() == 0) {
            return "There are no matching tasks in you task list.";
        } else {
            return "Here are the matching tasks in your list:\n"
                    + filteredTasks + filteredTasks.size() + " tasks "
                    + "match the keyword.";
        }
    }
}
