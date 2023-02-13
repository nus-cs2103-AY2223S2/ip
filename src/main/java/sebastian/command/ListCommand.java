package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;

/**
 * Class used to handle a command to retrieve the tasks currently on the task list
 */
public class ListCommand extends Command {

    /**
     * Retrieve all tasks on the list
     * @param taskList taskList instance created at the start of the session
     * @param ui ui instance created at the start of the session
     * @param storage storage instance created at the start of the session
     * @return a string representing the result of task execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getTotalTasks() == 0) {
            return ui.getFormattedString("You do not have any task yet");
        }
        String res = "As you wish. Here are the tasks in your list: " + "\n" + taskList + "\n"
                + "You have " + taskList.getTotalTasks() + " tasks";
        return ui.getFormattedString(res);
    }

}
