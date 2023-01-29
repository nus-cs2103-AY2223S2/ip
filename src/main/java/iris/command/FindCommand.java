package iris.command;

import iris.TaskList;
import iris.TaskStore;
import iris.Ui;
import iris.exception.IrisException;

/**
 * finds tasks corresponding to given keyword
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException {
        TaskList filtered = tasks.findTask(this.keyword);
        if (filtered.size() == 0) {
            Ui.output("There are no matching tasks in you task list.");
        } else {
            Ui.output("Here are the matching tasks in your list:");
            Ui.output(filtered.toString() + filtered.size() + " tasks match the keyword.");
        }
    }
}
