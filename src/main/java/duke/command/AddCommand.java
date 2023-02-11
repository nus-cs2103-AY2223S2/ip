package duke.command;

import duke.task.Task;

import duke.tasklist.TaskList;

/**
 * Represents a command that adds a Task to the list of Tasks.
 */
public class AddCommand extends Command {

    private final Task task;
    private final String taskType;

    public AddCommand(Task task, String taskType) {
        super(false);
        this.task = task;
        this.taskType = taskType;

    }

    /**
     * Executes the command by adding the given Task to the list of Tasks.
     *
     * @param taskList The TaskList object that manages the list of Tasks.
     * @return The String response of the chatbot.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.addTask(this.task, this.taskType);
    }

}
