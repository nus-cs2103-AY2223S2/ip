package duke.command;

import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {
    /**
     * Class constructor.
     */
    public ListCommand() {}

    /**
     * Returns the list of tasks.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return list of tasks.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.printList(taskList);
    }
}
