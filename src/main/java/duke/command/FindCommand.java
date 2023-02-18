package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class FindCommand extends Command {
    String command;

    /**
     * Class constructor.
     *
     * @param command the task to be found in the list
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Finds and returns the items in the list that include the keyword given.
     * Returns an error message otherwise.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return list of items.
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String keyword = command.substring(command.indexOf(" ") + 1);
        int[] indexArray = new int[taskList.getSize()];
        boolean isContained = false;

        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).toString().contains(keyword)) {
                indexArray[i] = 1;
                isContained = true;
            }
        }

        if (!isContained) {
            return "There are no such items in your list!";
        } else {
            return ui.printFind(taskList, indexArray);
        }
    }
}
