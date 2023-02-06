package boo.command;

import boo.datetime.DateTime;
import boo.storage.Storage;
import boo.task.Deadline;
import boo.tasklist.TaskList;

/**
 * Represents a deadline command that is entered by the user to create a task with a deadline.
 */
public class DeadlineCommand extends Command {

    /** Task name for the deadline task to be created. */
    private String taskName;

    /** Deadline for the given deadline task. */
    private String deadline;

    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Storage that allows updating after creating the deadline task. */
    private Storage storage;

    /**
     * Constructs a {@code DeadlineCommand}.
     *
     * @param taskName The name of the deadline task to be created.
     * @param deadline The deadline of the task.
     * @param tasks The lists of all available tasks.
     * @param storage The Storage object to allow local saving after adding a new deadline task.
     */
    public DeadlineCommand(String taskName, String deadline, TaskList tasks, Storage storage) {
        super();
        this.taskName = taskName;
        this.deadline = deadline;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Creates a deadline task and updates the local data file.
     *
     * @return a string informing the user that the file has been created.
     */
    @Override
    public String runCommand() {
        //Creates task and saves it
        Deadline newDeadlineTask = new Deadline(taskName, deadline, DateTime.getDateTimeObject(deadline));
        tasks.addTask(newDeadlineTask);
        storage.saveTasks(tasks);

        //Prepares output string
        StringBuilder sb = new StringBuilder();
        sb.append("Added task to list:\n");
        sb.append(newDeadlineTask.getStatusOfTaskInString() + "\n");
        if (tasks.getSizeOfTaskList() == 1) {
            sb.append("\nCurrently, there is 1 task in your list.");
        } else {
            sb.append("\nCurrently, there are " + tasks.getSizeOfTaskList() + " tasks in your list.");
        }
        return sb.toString();
    }
}
