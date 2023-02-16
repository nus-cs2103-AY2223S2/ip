package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Delete command which removes a task from the TaskList.
 */
public class Delete extends Command {

    /**
     * Constructor for Delete
     *
     * @param input The user input.
     */
    public Delete(String input) {
        super(input);
    }

    /**
     * Deletes the user-specified item in the Task List.
     *
     * @param tasks The current Task List.
     * @return The Task List with the specified item deleted.
     */
    public TaskList execute(TaskList tasks) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        tasks.remove(taskIndex);
        Ui.deleteMessage(tasks.get(taskIndex));
        return tasks;
    }
}