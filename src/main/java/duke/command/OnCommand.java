package duke.command;

import duke.datetime.DateTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.temporal.Temporal;

/**
 * Represents an on  command that is entered by the user to check what tasks are there on a specific day.
 */
public class OnCommand extends Command {
    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Date object representing the date to be checked. */
    private Temporal dateObject;

    /**
     * Constructs an OnCommand
     *
     * @param ui The Ui to allow the command to print messages to the user.
     * @param tasks The lists of all available tasks.
     * @param date The date to be checked.
     */
    public OnCommand(Ui ui, String date, TaskList tasks) {
        super(ui);
        this.dateObject = DateTime.getDateTimeObject(date);
        this.tasks = tasks;
    }

    /**
     * Prints out all the tasks that occur on the given date.
     */
    @Override
    public void runCommand() {
        Ui.printStraightLine();
        System.out.println("Tasks on: " + DateTime.formatDate(dateObject));
        int count = 1;
        //Iterate through each task and check
        for (int t = 0; t < tasks.getSizeOfTaskList(); t = t + 1) {
            Task currTask = tasks.getTask(t);
            if (currTask instanceof Deadline) {
                if (DateTime.isEqualDate(dateObject, ((Deadline) currTask).getDeadline())) {
                    ui.printStatement(Integer.toString(count) + ". " +
                            currTask.getStatusOfTaskInString());
                    count += 1;
                }
            }
            else if (currTask instanceof Event) {
                if (DateTime.isValidDuration(((Event) currTask).getStartDate(), dateObject) &&
                        DateTime.isValidDuration(dateObject, ((Event) currTask).getEndDate())) {
                    ui.printStatement(Integer.toString(count) + ". " +
                            currTask.getStatusOfTaskInString());
                    count += 1;
                }
            }
        }
        if (count == 1) {
            ui.printStatement("You have no tasks on this day.");
        }
        Ui.printStraightLine();
    }
}
