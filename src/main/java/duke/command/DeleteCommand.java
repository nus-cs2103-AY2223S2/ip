package duke.command;


import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete command entered by the user to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /** The task number to be deleted. */
    private int taskNumber;

    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Storage that allows updating after the delete command is completed. */
    private Storage storage;

    /**
     * Constructs an DeleteCommand.
     * @param ui The Ui to allow the command to print messages to the user.
     * @param taskNumber The number of the task which is to be deleted.
     * @param tasks The lists of all available tasks.
     * @param storage The Storage object to allow local saving of the deletion.
     */
    public DeleteCommand(Ui ui, int taskNumber, TaskList tasks, Storage storage) {
        super(ui);
        this.taskNumber = taskNumber;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Marks a given task as done.
     */
    @Override
    public void runCommand() {
        //Updates and print changes
        tasks.deleteTask(taskNumber);
        Ui.printStraightLine();
        System.out.println("Poof! One less worry. The following task is now marked as done:");
        System.out.println(tasks.getTask(taskNumber).getStatusOfTaskInString());
        Ui.printStraightLine();

        //Save changes
        storage.saveTasks(tasks);
    }





}
