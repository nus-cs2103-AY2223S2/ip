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
    private final static String MATCHING_TASKS_MESSAGE = "Here are the tasks matching \"%s\" :\n";
    private final static String NO_MATCHING_TASKS_MESSAGE = "No tasks matching \"%s\" were found.\n";

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
            TaskList matchedTaskList = getMatchedTasks(tasks, description);
            message.append(getMatchedTasksMessage(matchedTaskList, description));
        }
        ui.appendResponse(message.toString());
    }

    /**
     * Method that takes in a list of tasks and a description and returns a list of tasks that match the given description.
     *
     * @param tasks The list of tasks to be searched
     * @param description The description of the task to be searched
     * @return A list of tasks that match the given description
     */
    private TaskList getMatchedTasks(TaskList tasks, String description) {
        TaskList matchedTaskList = new TaskList();
        for (DukeTask task : tasks.getTasks()) {
            if (task.matches(description)) {
                matchedTaskList.addTask(task);
            }
        }
        return matchedTaskList;
    }

    /**
     * Gets the message for matched tasks.
     *
     * @param matchedTaskList The list of matched tasks
     * @param description The description used to find the tasks
     * @return The message for matched tasks
     */
    private String getMatchedTasksMessage(TaskList matchedTaskList, String description) {
        if (!matchedTaskList.getTasks().isEmpty()) {
            return String.format(MATCHING_TASKS_MESSAGE, description) + matchedTaskList + "\n";
        } else {
            return String.format(NO_MATCHING_TASKS_MESSAGE, description);
        }
    }
}

