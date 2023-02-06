package boo.command;

import boo.storage.Storage;
import boo.task.Task;
import boo.tasklist.TaskList;

/**
 * Represents a mark command that is entered by the user to mark a task as done.
 */
public class MarkCommand extends Command {

    /** Task number associated with this mark command. */
    private int taskNumber;

    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Storage that allows updating after the mark command is completed. */
    private Storage storage;

    /**
     * Constructs a MarkCommand.
     *
     * @param taskNumber The number of the task which is to be marked as done.
     * @param tasks The {@code TaskList} of all available tasks.
     * @param storage The {@code Storage} object to allow local saving of the mark operation.
     */
    public MarkCommand(int taskNumber, TaskList tasks, Storage storage) {
        super();
        this.taskNumber = taskNumber;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Marks a given task as done.
     */
    @Override
    public String runCommand() {
        //Updates changes
        Task currentTask = tasks.getTask(taskNumber);
        currentTask.setDoneStatus();

        //Save changes
        storage.saveTasks(tasks);

        //Inform user
        StringBuilder sb = new StringBuilder();
        sb.append("Poof! One less worry. The following task is now marked as done:\n\n");
        sb.append(currentTask.getStatusOfTaskInString());
        return sb.toString();
    }
}
