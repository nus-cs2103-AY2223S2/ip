package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;

/**
 * List command to show the current task list.
 */
public class ListCommand extends Command {

    /**
     * Method to execute the list command.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String Output of list stored in the textfile.
     */
    public String execute(TaskList tasks, StorageList storage) {
        return tasks.printList();
    }

}

