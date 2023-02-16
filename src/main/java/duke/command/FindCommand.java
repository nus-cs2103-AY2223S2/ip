package duke.command;

import duke.TaskList;

/**
 * A command representing the user searching for tasks in task list that contain
 * a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * A constructor for FindCommand.
     *
     * @param tasks TaskList object to find tasks that contain keyword
     * @param keyword Keyword to search for
     */
    public FindCommand(TaskList tasks, String keyword) {
        super(tasks);
        this.keyword = keyword;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() {
        TaskList tasksFound = tasks.findTasks(keyword);
        return tasksFound.getListOfTasks();
    }
}
