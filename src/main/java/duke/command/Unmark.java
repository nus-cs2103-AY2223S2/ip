package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Unmark command which marks a task as not done.
 */
public class Unmark extends Command {

    /**
     * Constructor for Unmark
     *
     * @param input The user input.
     */
    public Unmark(String input) {
        super(input);
    }

    /**
     * Unmarks the user-specified item in the Task List.
     *
     * @param tasks The current Task List.
     * @return The Task List with the specified item unmarked.
     */
    @Override
    public TaskList execute(TaskList tasks) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        tasks.get(taskIndex).markAsNotDone();
        Ui.unMarkMessage(tasks.get(taskIndex));
        return tasks;
    }
}
