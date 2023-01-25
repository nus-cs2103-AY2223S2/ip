package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete command entered by the user to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /** The task index to be deleted. */
    private int taskIndex;

    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Storage that allows updating after the delete command is completed. */
    private Storage storage;

    /**
     * Constructs a DeleteCommand.
     * @param ui The Ui to allow the command to print messages to the user.
     * @param taskIndex The index of the task which is to be deleted.
     * @param tasks The lists of all available tasks.
     * @param storage The Storage object to allow local saving of the deletion.
     */
    public DeleteCommand(Ui ui, int taskIndex, TaskList tasks, Storage storage) {
        super(ui);
        this.taskIndex = taskIndex;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Deletes a task from the task list.
     */
    @Override
    public void runCommand() {
        //Updates and print changes
        Task deletedTask = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        Ui.printStraightLine();
        ui.printStatement("Ta-da! The following task has been deleted.");
        ui.printStatement(deletedTask.getStatusOfTaskInString());
        Ui.printStraightLine();

        //Save changes
        storage.saveTasks(tasks);
    }

}
