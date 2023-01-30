package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A command that stores the command to list the tasks in the task list. The action of adding the task can be carried
 * out when called.
 */
public class ListCommand extends Command {
    /**
     * Constructor for a command to list the tasks saved in the task list.
     *
     * @param COMMAND_STRING The list command in string representation
     */
    public ListCommand(String COMMAND_STRING) {
        super(Commands.LIST, COMMAND_STRING);
    }

    /**
     * Constructor for a command to list out the tasks in the task list in their string representation.
     *
     * @param tasks   List of tasks that are stored
     * @param ui      UI to deal with the visual output
     * @param storage Storage to deal with input and output of data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msgHeader = "Current data in the list are:";
        ui.showMsg(msgHeader);

        ArrayList<Task> t = tasks.getTasks();
        for (int i = 0; i < t.size(); i++) {
            String output = String.format("%d. %s", i + 1, t.get(i));
            ui.showMsg(output);
        }
    }
}
