package commands;

import files.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command which deletes a task into a task list.
 */
public class DeleteTaskCommand extends Command {
    private String indexString;

    /**
     * Constructor to create a command which tells Duke to delete a specific task to the task list.
     * @param command command line input to add task
     */
    public DeleteTaskCommand(String command) {
        super();
        this.indexString = command;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of deleting a task into the task list.
     * @param taskList task list to delete task from
     * @param ui user interface
     * @param storage storage for reading and writing data to files
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task taskToBeDeleted = taskList.get(this.indexString);
            taskList.deleteTask(this.indexString);
            return String.format("Removed this task I have:\n %s\nLeft with %d tasks, you have",
                    taskToBeDeleted.toString(), taskList.getSize());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
