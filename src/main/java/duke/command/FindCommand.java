package duke.command;

import java.util.ArrayList;

import duke.storage.StorageList;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This class builds a find command object to allow string to be matched.
 */
public class FindCommand extends Command {
    private String message;

    /**
     * Constructor for find command.
     *
     * @param fullCommand Input for command to be found in task list.
     */
    public FindCommand(String fullCommand) {
        String[] findCommand = fullCommand.split("find ");
        this.message = findCommand[1];
    }

    /**
     * Method to execute the finding of tasks in the task list.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String
     */
    public String execute(TaskList tasks, StorageList storage) {
        ArrayList<Task> tasksList = tasks.findStringInTask(message);
        String stringOutputOfTask = "";
        for (Task t : tasksList) {
            stringOutputOfTask += t + "\n";
        }

        return "Here are the matching tasks in your list: " + "\n" + stringOutputOfTask;
    }

}
