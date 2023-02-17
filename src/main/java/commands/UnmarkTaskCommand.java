package commands;

import files.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command which unmarks a task into a task list.
 */
public class UnmarkTaskCommand extends Command {

    private String taskIndex;

    /**
     * Constructor to create a command which tells Duke to unmark a specific task to the task list.
     * @param taskIndex index of task for Duke to unmark
     */
    public UnmarkTaskCommand(String taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of unmarking a task into the task list.
     * @param taskList task list to unmark task from
     * @param ui user interface
     * @param storage storage for reading and writing data to files
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(taskIndex);
            return String.format("Ok! Marked this task as not done yet, I have:\n %s",
                    taskList.get(taskIndex).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
