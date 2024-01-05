package duke.command;

import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A FindCommand class that take in a description and find all tasks that match the given
 * description.
 */
public class FindCommand extends Command {
    private static final String MATCHING_TASKS_MESSAGE = "Here are the tasks matching \"%s\" :\n";
    private static final String NO_MATCHING_TASKS_MESSAGE = "No tasks matching \"%s\" were found.\n";
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
     * Executes the command and displays the matched tasks based on the provided descriptions.
     * @param tasks TaskList containing all the tasks
     * @param ui Ui object for displaying the matched tasks
     * @param storage Storage object for accessing the storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder();
        for (String description : this.descriptions) {
            // Get the matched tasks based on the provided description
            TaskList matchedTaskList = getMatchedTasks(tasks, description);
            // Append the matched tasks message for the current description
            message.append(getMatchedTasksMessage(matchedTaskList, description));
        }
        // Display the final message containing matched tasks for all the descriptions
        ui.appendResponse(message.toString());
    }

    /**
     * This method takes in a task list and a description as input and returns a task list containing tasks that match
     * the given description.
     *
     * @param tasks - the task list to be searched
     * @param description - the description to be searched for
     * @return - a task list containing tasks that match the given description
     */
    private TaskList getMatchedTasks(TaskList tasks, String description) {
        TaskList matchedTaskList = new TaskList();
        for (DukeTask task : tasks.getTasks()) {
            // check if each task matches the given description
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
        // Initialize a message string
        String message = "";
        // Check if the matched task list is not empty
        if (!matchedTaskList.getTasks().isEmpty()) {
            // If there are matched tasks, add them to the message string with a formatted message
            message = String.format(MATCHING_TASKS_MESSAGE, description) + matchedTaskList + "\n";
        } else {
            // If there are no matched tasks, add a no matching tasks message to the message string
            message = String.format(NO_MATCHING_TASKS_MESSAGE, description);
        }

        // return the message string
        return message;
    }
}

