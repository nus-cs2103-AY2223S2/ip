package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.TaskContainer;
import twofive.ui.Ui;

/**
 * Filters task with description containing with keyword when the command is executed.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints out all tasks added with the keyword in their description given a task list.
     *
     * @param tasks List of all current tasks.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskContainer.setTasks(tasks.getTasksByKeyword(this.keyword));
        return "Here are the tasks in your list with keyword ["
                + keyword
                + "] in "
                + "their description";
    }
}
