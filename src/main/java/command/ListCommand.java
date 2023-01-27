package command;

import task.TaskList;
import ui.TextUi;

public class ListCommand extends CommandClass {

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public ListCommand(String command, boolean doesPrint) {
        super(command, doesPrint, false);
    }

    public void execute(TaskList taskList, TextUi ui) {
        String taskListString = "Here are the tasks in your list:\n" +
                taskList.getTaskListString(true);
        uiPrint(ui, taskListString);
    }
}
