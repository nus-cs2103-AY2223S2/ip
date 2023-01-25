package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
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
     * @param ui The <code>Ui</code> to allow the command to print messages to the user.
     * @param taskNumber The number of the task which is to be marked as done.
     * @param tasks The <code>TaskList</code> of all available tasks.
     * @param storage The <code>Storage</code> object to allow local saving of the mark operation.
     */
    public MarkCommand(Ui ui, int taskNumber, TaskList tasks, Storage storage) {
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
        Task currentTask = tasks.getTask(taskNumber);
        currentTask.setDoneStatus();
        Ui.printStraightLine();
        ui.printStatement("Poof! One less worry. The following task is now marked as done:");
        ui.printStatement(currentTask.getStatusOfTaskInString());
        Ui.printStraightLine();

        //Save changes
        storage.saveTasks(tasks);
    }
}
