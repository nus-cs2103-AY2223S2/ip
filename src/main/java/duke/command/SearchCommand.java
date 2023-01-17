package duke.command;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A FindCommand class that take in a description and find all tasks that match the given
 * description.
 */
public class SearchCommand extends Command {
    private final LocalDate date;

    /**
     * The constructor of FinaCommand that takes in the description of the tasks to be found.
     *
     * @param date The description of the Task to be found.
     */
    public SearchCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Displays all the matching tasks with their respective types and status.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTaskList = new TaskList();
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            DukeTask currentTask = tasks.getTask(i);
            if (tasks.getTask(i).matchesDate(this.date)) {
                matchedTaskList.addTask(currentTask);
            }
        }
        String message = "Here are the matching tasks in your list:\n" + matchedTaskList;
        ui.appendResponse(message);
    }
}
