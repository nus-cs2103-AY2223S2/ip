package duke.command;

import duke.display.Ui;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;

/**
 * A FindCommand class that take in a description and find all tasks that match the given
 * description.
 */
public class FindCommand extends Command {
    private final String description;

    /**
     * The constructor of FinaCommand that takes in the description of the tasks to be found.
     *
     * @param description The description of the Task to be found.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Displays all the matching tasks with their respective types and status.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTaskList = new TaskList();
        for (int i = 0; i < tasks.remainingTasks(); i++) {
            DukeTask currentTask = tasks.getTask(i);
            if (tasks.getTask(i).matches(this.description)) {
                matchedTaskList.addTask(currentTask);
            }
        }
        ui.displayWithBar("Here are the matching tasks in your list:\n" + matchedTaskList);
    }
}
