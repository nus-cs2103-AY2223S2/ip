package duke.command;

import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * A FindCommand class that take in a description and find all tasks that match the given
 * description.
 */
public class FindCommand extends Command {
    private final String[] descriptions;

    /**
     * The constructor of FinaCommand that takes in the description of the tasks to be found.
     *
     * @param descriptions The description of the Task to be found.
     */
    public FindCommand(String... descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Displays all the matching tasks with their respective types and status.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (String description : this.descriptions) {
            TaskList matchedTaskList = new TaskList();
            for (int j = 0; j < tasks.getNoOfTasks(); j++) {
                DukeTask currentTask = tasks.getTask(j);
                if (tasks.getTask(j).matches(description)) {
                    matchedTaskList.addTask(currentTask);
                }
            }

            String message = String.format("Here are the tasks matching \"%s\" :\n", description)  + matchedTaskList;
            ui.appendResponse(message + "\n");
        }
    }
}
