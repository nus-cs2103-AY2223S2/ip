package commands;

import java.time.LocalDate;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * This class is used to find tasks
 */
public class FindCommand extends Command {

    /** Date to check */
    private LocalDate date = null;

    /** Word or Phrase to search */
    private String keyword = null;

    /**
     * Creates an executable command to find tasks consisting of specified date
     *
     * @param date parsed user specified date
     */
    public FindCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Creates an executable command to find tasks containing a specfied keyword
     *
     * @param keyword user specified keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks in task list that contain a specified keyword or date
     *
     * @param taskList the task list to check
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @return {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        TaskList taskContainSearchList = new TaskList();

        for (Task t: taskList) {
            if (this.date != null) {
                if (t.contains(this.date)) {
                    taskContainSearchList.add(t);
                }
            } else if (this.keyword != null) {
                if (t.contains(this.keyword)) {
                    taskContainSearchList.add(t);
                }
            }
        }

        int len = taskContainSearchList.size();
        return "You have " + (len == 0
                                ? "no tasks"
                                : len + (len > 1
                                        ? " task"
                                        : " tasks"))
                + " containing the following search term:\n    "
                + (this.date != null
                    ? this.date.toString()
                    : (this.keyword != null
                        ? this.keyword
                        : ""))
                + (len == 0 ? "" : "\n")
                + taskContainSearchList.getAllAsString();
    }
}
