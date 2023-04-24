package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.TaskContainer;

/**
 * Returns a list of task with the given tag when command is executed.
 */
public class TagListCommand extends Command {
    private String tag;


    public TagListCommand(String t) {
        tag = t;
    }

    /**
     * Prints out all tasks added with the tag given a task list.
     *
     * @param tasks List of all current tasks.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert !tag.isEmpty() : "Tag should not be empty";
        TaskContainer.setTasks(tasks.getTasksByTag(tag));
        return "Here is a list of tasks with the tag ["
                + tag
                + "]";
    }
}
