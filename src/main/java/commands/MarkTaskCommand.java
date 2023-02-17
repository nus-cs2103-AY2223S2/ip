package commands;

import files.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command which marks a task into a task list.
 */
public class MarkTaskCommand extends Command {
    private String taskIndex;

    /**
     * Constructor to create a command which tells Duke to mark a specific task to the task list.
     * @param taskIndex index of task for Duke to mark
     */
    public MarkTaskCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }


    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of marking a task into the task list.
     * @param taskList task list to mark task from
     * @param ui user interface
     * @param storage storage for reading and writing data to files
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTask(taskIndex);
            return String.format("Nice! Marked this task as done, I have:\n %s",
                    taskList.get(taskIndex).toString());
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
