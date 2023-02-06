package boo.command;

import boo.storage.Storage;
import boo.task.Task;
import boo.tasklist.TaskList;

/**
 * Represents an unmark command that is entered by the user to mark a task as undone.
 */
public class UnmarkCommand extends Command {

    /** Task number associated with this unmark command. */
    private int taskNumber;

    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Storage that allows updating after the unmark command is completed. */
    private Storage storage;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param taskNumber The number of the task which is to be marked as undone.
     * @param tasks The {@code TaskList} of all available tasks.
     * @param storage The {@code Storage} object to allow local saving of the unmark operation.
     */
    public UnmarkCommand(int taskNumber, TaskList tasks, Storage storage) {
        super();
        this.taskNumber = taskNumber;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Marks a given task as undone.
     *
     * @return a string indicating that the task has been marked as undone.
     */
    @Override
    public String runCommand() {
        //Updates and print changes
        Task currentTask = tasks.getTask(taskNumber);
        currentTask.setUndoneStatus();

        //Save changes
        storage.saveTasks(tasks);

        //Prepare string
        StringBuilder sb = new StringBuilder();
        sb.append("Alright! The following task is now marked as undone. I will help you keep an eye on it.\n\n");
        sb.append(currentTask.getStatusOfTaskInString());

        return sb.toString();
    }
}
