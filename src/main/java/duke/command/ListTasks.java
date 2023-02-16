package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * ListTasks command which lists out the current TaskList.
 */
public class ListTasks extends Command {

    /**
     * Constructor for ListTasks
     *
     * @param input The user input.
     */
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