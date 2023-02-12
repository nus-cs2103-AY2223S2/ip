package command;

import duke.TaskList;

/**
 * Command to see everything in the list.
 */
public class CommandList extends Command {
    private final TaskList taskList;

    /**
     * Constructor for CommandList.
     *
     * @param taskList List of all tasks.
     */
    public CommandList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        return this.getAllItemIn(this.taskList);
    }

    private String getAllItemIn(TaskList taskList) {
        return taskList.toString();
    }
}
