package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Marks a Task as done.
 */
public class Mark extends Command {

    /**
     * Constructor for Mark
     *
     * @param input The user input.
     */
    public Mark(String input) {
        super(input);
    }

    /**
     * Marks the user-specified item in the Task List as done.
     *
     * @param tasks The current Task List.
     * @return The Task List with the specified item marked done.
     */
    public TaskList execute(TaskList tasks) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        tasks.get(taskIndex).markAsDone();
        Ui.markMessage(tasks.get(taskIndex));
        return tasks;
    }
}