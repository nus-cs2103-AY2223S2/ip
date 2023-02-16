package duke.command;

import duke.TaskList;
import duke.tag.Tag;

import java.util.Arrays;

/**
 * A command representing the user viewing the full list of tasks in the task list.
 */
public class ListCommand extends Command {
    private String tagName;

    /**
     * A constructor for ListCommand.
     *
     * @param tasks TaskList object to be viewed.
     */
    public ListCommand(TaskList tasks, String ... tagName) {
        super(tasks);
        this.tagName = (Arrays.equals(tagName, new String[0])) ? null : tagName[0];
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() {
        if (tagName == null) {
            return tasks.toString();
        } else {
            Tag tag = new Tag(tagName);
            TaskList tasksFound = tasks.listTagged(tag);
            return tasksFound.getListOfTasks();
        }
    }
}
