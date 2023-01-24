package duke.command;

import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A FindCommand class that take in a description and find all tasks that match the given
 * description.
 */
public class FindCommand extends Command {
    private final String[] descriptions;
    private static final String MATCHING_TASKS_MESSAGE = "Here are the tasks matching \"%s\" :\n";
    private static final String NO_MATCHING_TASKS_MESSAGE = "No tasks matching \"%s\" were found.\n";

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
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) {
        StringBuilder message = new StringBuilder();
        for (String description : this.descriptions) {
            TaskList matchedTaskList = new TaskList();
            for (DukeTask task : tasks.getTasks()) {
                if (task.matches(description)) {
                    matchedTaskList.addTask(task);
                }
            }
            if (!matchedTaskList.getTasks().isEmpty()) {
                message.append(String.format(MATCHING_TASKS_MESSAGE, description));
                message.append(matchedTaskList).append("\n");
            } else {
                message.append(String.format(NO_MATCHING_TASKS_MESSAGE, description));
            }
        }
        ui.appendResponse(message.toString());
    }

}
