package command;

import duke.Duke;
import duke.DukeException;
import task.TaskList;
import ui.TextUi;

/**
 * List commands
 */
public class ListCommand extends Command {

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public ListCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, 1);
    }

    /**
     * Execute the task
     * @param taskList the list of tasks
     * @param ui       a text UI
     */
    public void execute(TaskList taskList, TextUi ui) {
        String taskListString = "Here are the tasks in your list:\n"
                + taskList.getTaskListString(true);
        uiPrint(ui, taskListString);
    }

    public String execute(TaskList taskList) {
        String taskListString = "Here are the tasks in your list:\n"
                + taskList.getTaskListString(true);
        return taskListString;
    }
}
