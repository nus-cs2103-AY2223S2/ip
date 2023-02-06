package duke.command;

import duke.TaskList;

/**
 * A command representing the user viewing the full list of tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * A constructor for ListCommand.
     *
     * @param tasks TaskList object to be viewed.
     */
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute() {
        return tasks.toString();
    }
}
