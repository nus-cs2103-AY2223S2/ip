package duke.command;

import duke.DukeException;
import duke.task.TaskList;

/**
 * A command that returns the whole list of tasks currently stored.
 * @author Junyi
 */
public class ListCommand extends Command {

    private final TaskList taskList;

    /**
     * Constructor for ListCommand.
     * Displays the tasks in the current task list.
     * @param taskList TaskList of Duke's tasks.
     */
    private ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Factory method to create list command from user input string
     * @param taskList TaskList of Duke's tasks.
     * @return An instance of ListCommand.
     */
    public static ListCommand createListCommand(TaskList taskList) {
        return new ListCommand(taskList);
    }

    @Override
    public String execute() throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Arii has retrieved your current tasks...\n");
        for (int i = 0; i < taskList.size(); i++) {
            stringBuilder.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        return stringBuilder.toString();
    }
}
