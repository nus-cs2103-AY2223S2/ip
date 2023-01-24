package duke.command;

import duke.datetime.DateTime;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
     * Constructs a DeadlineCommand.
     *
     * @param ui The Ui to allow the command to print messages to the user.
     * @param taskName The name of the deadline task to be created.
     * @param deadline The deadline of the task.
     * @param tasks The lists of all available tasks.
     * @param storage The Storage object to allow local saving after adding a new deadline task.
     */
    public DeadlineCommand(Ui ui, String taskName, String deadline,TaskList tasks, Storage storage) {
        super(ui);
        this.taskName = taskName;
        this.deadline = deadline;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Creates a deadline task and updates the local data file.
     */
    @Override
    public void runCommand() {
        //Creates task and saves it
        Deadline newDeadlineTask = new Deadline(taskName, deadline, DateTime.getDateTimeObject(deadline));
        tasks.addTask(newDeadlineTask);
        storage.saveTasks(tasks);

        //Notifies the user
        Ui.printStraightLine();
        ui.printStatement("Added task to list:");
        ui.printStatement(newDeadlineTask.getStatusOfTaskInString());
        if (tasks.getSizeOfTaskList() == 1) {
            ui.printStatement("\nCurrently, there is 1 task in your list.");
        } else {
            ui.printStatement("\nCurrently, there are " + Integer.toString(tasks.getSizeOfTaskList())
                    + " tasks in your list.");
        }
        Ui.printStraightLine();
    }
}

