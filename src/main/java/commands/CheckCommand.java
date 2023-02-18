package commands;

import java.time.LocalDate;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * This class is to return tasks that consist of specified date
 */
public class CheckCommand extends Command {
    /** Date to check */
    private LocalDate date;

    /**
     * Constructs command to let user know tasks that consist of specified date
     *
     * @param date parsed user specified date
     */
    public CheckCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Checks whether a task falls within a specified date
     *
     * @param taskList the tasklist to check
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList taskContainDateList = new TaskList();
        for (Task t: taskList) {
            if (t.contains(this.date)) {
                taskContainDateList.add(t);
            }
        }

        int len = taskContainDateList.size();
        ui.printResponse("You have " + (len == 0
                                        ? "no tasks"
                                        : len + (len > 1
                                                ? " task"
                                                : " tasks"))
                        + " on " + this.date.toString()
                        + taskContainDateList.getAllAsString());
    }
}
