package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListTasks extends Command {

    public ListTasks(String input) {
        super(input);
    }

    /**
     * Lists the current tasks to the user.
     *
     * @param tasks The current Task List.
     * @return The current Task List.
     */
    public TaskList execute(TaskList tasks) {
        Ui.listMessage(tasks);
        return tasks;
    }
}