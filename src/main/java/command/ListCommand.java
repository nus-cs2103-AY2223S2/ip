package command;

import task.TaskList;
import ui.TextUi;

/**
 * List commands
 */
public class ListCommand extends CommandClass {

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public ListCommand(String command, boolean doesPrint) {
        super(command, doesPrint);
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
}
