package duke.command;

import java.time.LocalDate;

import duke.exception.InvalidInputException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A SearchCommand class that takes in a date and finds all tasks that take place on that date.
 */
public class SearchCommand extends Command {
    private static final String ERROR_MESSAGE = "OOPS!!! No tasks found on the specified date";
    private final LocalDate date;

    /**
     * The constructor of SearchCommand that takes in the date of the tasks to be found.
     *
     * @param date The date of the tasks to be found.
     */
    public SearchCommand(LocalDate date) {
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
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) throws InvalidInputException {
        TaskList matchedTaskList = new TaskList();
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            if (tasks.getTask(i).matchesDate(this.date)) {
                matchedTaskList.addTask(tasks.getTask(i));
            }
        }
        if (matchedTaskList.getNoOfTasks() == 0) {
            throw new InvalidInputException(ERROR_MESSAGE);
        } else {
            String message = "Here are the tasks on the specified date:\n" + matchedTaskList;
            ui.appendResponse(message);
        }
    }
}
