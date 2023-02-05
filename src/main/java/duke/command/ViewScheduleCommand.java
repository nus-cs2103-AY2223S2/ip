package duke.command;

import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * A ViewScheduleCommand class that takes in a date and finds all tasks that take place on that date.
 */
public class ViewScheduleCommand extends Command {
    private final LocalDate date;
    private static final String TASK_ON_DATE_MESSAGE = "Here are the tasks on the specified date:\n";
    private static final String NO_TASK_ON_DATE_ERROR = "There are no tasks on the specified date.";


    /**
     * The constructor of ViewScheduleCommand that takes in the date of the tasks to be found.
     *
     * @param date The date of the tasks to be found.
     */
    public ViewScheduleCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Displays all the tasks that take place on the specified date.
     *
     * @param tasks The user TaskList that contains all the tasks to be searched
     * @param ui The Ui object used to display information
     * @param storage The Storage object used to save and load the TaskList
     * @throws InvalidInputException when no tasks are found on the specified date
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        //Find tasks that match the specified date
        TaskList matchedTasks = findTasksOnDate(tasks, this.date);

        //If no tasks are found on the specified date, throw an exception
        if (matchedTasks.isEmpty()) {
            throw new InvalidInputException(NO_TASK_ON_DATE_ERROR);
        } else {
            //Otherwise, display the tasks found on the specified date
            String message = TASK_ON_DATE_MESSAGE + matchedTasks;
            ui.appendResponse(message);
        }
    }


    /**
     * Finds all tasks in the given {@code tasks} list that match the given {@code date}
     *
     * @param tasks The list of tasks to search through
     * @param date The date to match the tasks against
     * @return A new TaskList containing all tasks that match the given date
     */
    private TaskList findTasksOnDate(TaskList tasks, LocalDate date) {
        TaskList matchedTasks = new TaskList();
        //iterate through all tasks
        for (DukeTask task : tasks.getTasks()) {
            //check if task matches the given date
            if (task.matchesDate(date)) {
                //add task to the list of matched tasks
                matchedTasks.addTask(task);
            }
        }
        return matchedTasks;
    }
}
