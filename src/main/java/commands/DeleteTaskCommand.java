package commands;


import exceptions.DukeException;
import files.Storage;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.deleteTask(this.indexString);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
