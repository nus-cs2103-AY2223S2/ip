package duke.command;

import java.util.ArrayList;

import duke.storage.StorageList;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class builds a find command object to allow string to be matched.
 */
public class FindCommand extends Command {
    private String message;

    /**
     * @param fullCommand
     */
    public FindCommand(String fullCommand) {
        String[] checker = fullCommand.split("find ");
        this.message = checker[1];
    }

    /**
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return
     */
    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        System.out.println("Here are the matching tasks in your list:");
        ArrayList<Task> arrList = tasks.find(message);
        for (Task t : arrList) {
            System.out.println(t);
        }

        return true;
    }

}
