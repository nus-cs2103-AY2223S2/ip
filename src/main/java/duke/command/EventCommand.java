package duke.command;

import duke.datetime.DateTime;
import duke.storage.Storage;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an event command that is entered by the user to create a task with a start and end date.
 */
public class EventCommand extends Command {

    /** Task name for the event task to be created. */
    private String taskName;

    /** Start date for the given event task. */
    private String startDate;

    /** End date for the given event task. */
    private String endDate;

    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Storage that allows updating after creating the event task. */
    private Storage storage;

    /**
     * Constructs an EventCommand.
     *
     * @param ui The Ui to allow the command to print messages to the user.
     * @param taskName The name of the event task to be created.
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     * @param tasks The lists of all available tasks.
     * @param storage The Storage object to allow local saving after adding a new deadline task.
     */
    public EventCommand(Ui ui, String taskName, String startDate, String endDate, TaskList tasks, Storage storage) {
        super(ui);
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Creates an Event task and updates the local data file.
     */
    @Override
    public void runCommand() {
        //Creates task and saves it
        Event newEventTask = new Event(taskName, startDate, endDate, DateTime.getDateTimeObject(startDate),
                DateTime.getDateTimeObject(endDate));
        tasks.addTask(newEventTask);
        storage.saveTasks(tasks);

        //Notifies the user
        Ui.printStraightLine();
        ui.printStatement("Added task to list:");
        ui.printStatement(newEventTask.getStatusOfTaskInString());
        if (tasks.getSizeOfTaskList() == 1) {
            ui.printStatement("\nCurrently, there is 1 task in your list.");
        } else {
            ui.printStatement("\nCurrently, there are " + Integer.toString(tasks.getSizeOfTaskList())
                    + " tasks in your list.");
        }
        Ui.printStraightLine();
    }
}

