package boo.command;

import boo.storage.Storage;
import boo.task.Task;
import boo.tasklist.TaskList;

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
     * Constructs a {@code DeleteCommand}.
     *
     * @param taskIndex The index of the task which is to be deleted.
     * @param tasks The {@code TaskList} of all available tasks.
     * @param storage The {@code Storage} object to allow local saving of the deletion.
     */
    public DeleteCommand(int taskIndex, TaskList tasks, Storage storage) {
        this.taskIndex = taskIndex;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Deletes a task from the task list.
     *
     * @return a string that indicates to the user that the task has been deleted.
     */
    @Override
    public String runCommand() {
        //Updates and print changes
        Task deletedTask = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);

        //Save changes
        storage.saveTasks(tasks);

        //Prepare output
        StringBuilder sb = new StringBuilder();

        sb.append("Ta-da! The following task has been deleted.\n");
        sb.append(deletedTask.getStatusOfTaskInString());

        return sb.toString();
    }
}
