package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
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
     * @param ui The Ui to allow the command to print messages to the user.
     * @param taskNumber The number of the task which is to be marked as undone.
     * @param tasks The lists of all available tasks.
     * @param storage The Storage object to allow local saving of the unmark operation.
     */
    public UnmarkCommand(Ui ui, int taskNumber, TaskList tasks, Storage storage) {
        super(ui);
        this.taskNumber = taskNumber;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Marks a given task as undone.
     */
    @Override
    public void runCommand() {
        //Updates and print changes
        Task currentTask = tasks.getTask(taskNumber);
        currentTask.setUndoneStatus();
        Ui.printStraightLine();
        ui.printStatement("Alright! The following task is now marked as undone. I will help you keep an eye on it.");
        ui.printStatement(currentTask.getStatusOfTaskInString());
        Ui.printStraightLine();

        //Save changes
        storage.saveTasks(tasks);
    }
}
