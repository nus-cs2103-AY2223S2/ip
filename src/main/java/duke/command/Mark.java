package duke.command;

import duke.TaskList;
import duke.Ui;

public class Mark extends Command {

    public Mark(String input) {
        super(input);
    }

    /**
     * Marks the user-specified item in the Task List.
     *
     * @param tasks The current Task List.
     * @return The Task List with the specified item marked.
     */
    public TaskList execute(TaskList tasks) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        tasks.get(taskIndex).markAsDone();
        Ui.markMessage(tasks.get(taskIndex));
        return tasks;
    }
}