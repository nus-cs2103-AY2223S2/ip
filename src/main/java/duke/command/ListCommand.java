package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.textui.TextUi;

/**
 * A command that stores the command to list the tasks in the task list. The action of listing the task can be carried
 * out when called.
 */
public class ListCommand extends Command {
    /**
     * Constructor for a command to list the tasks saved in the task list.
     *
     * @param commandString The list command in string representation
     */
    public ListCommand(String commandString) {
        super(AvailableCommands.LIST, commandString);
    }

    /**
     * Constructor for a command to list out the tasks in the task list in their string representation.
     *
     * @param taskList List of tasks that are stored
     * @param ui       UI to deal with the visual output
     * @param storage  Storage to deal with input and output of data
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException {
        String msgHeader = "Current data in the list are:";
        String output = ui.showMsg(msgHeader);

        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String msg = String.format("%d. %s", i + 1, tasks.get(i));
            output += "\n";
            output += ui.showMsg(msg);
        }

        return output;
    }
}
