package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.exception.IrisException;

/**
 * finds tasks corresponding to given keyword
 */
public class FindCommand extends Command {
    private TaskList filtered;
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, TaskStore taskStore) throws IrisException {
        this.filtered = tasks.findTask(this.keyword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) {
        if (filtered.size() == 0) {
            return "There are no matching tasks in you task list.";
        } else {
            return "Here are the matching tasks in your list:\n"
                    + filtered + filtered.size() + " tasks "
                    + "match the keyword.";
        }
    }
}
