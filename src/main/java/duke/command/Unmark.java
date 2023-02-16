package duke.command;

import duke.TaskList;
import duke.Ui;

public class Unmark extends Command {

    public Unmark(String input) {
        super(input);
    }

    /**
     * Unmarks the user-specified item in the Task List.
     *
     * @param tasks The current Task List.
     * @return The Task List with the specified item unmarked.
     */
    public TaskList execute(TaskList tasks) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        tasks.get(taskIndex).markAsNotDone();
        Ui.unMarkMessage(tasks.get(taskIndex));
        return tasks;
    }
}
