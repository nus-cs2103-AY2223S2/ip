package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command that lists the existing tasks currently being stored.
 */
public class ListCommand extends Command {
    /**
     * Constructs a new ListCommand with the CommandType of LIST.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Executes this ListCommand with a specified TaskList, Ui, and Storage.
     * Iterates through the existing TaskList in order to display the existing tasks
     * using the Ui
     *
     * @param list the existing TaskList
     * @param ui the Ui to help inform the user of all the stored tasks
     * @param storage the Storage storing the latest TaskList (not used in this command)
     *
     * @return The execution result string.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n ");
        sb.append(list.toString());
        return sb.toString();
    }
}
