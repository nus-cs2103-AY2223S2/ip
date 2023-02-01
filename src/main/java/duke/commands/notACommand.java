package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;

/**
 * The class for invalid user inputs.
 */
public class notACommand extends Command {
    /**
     * the explicit default constructor
     */
    public notACommand() {
    }

    /**
     * Tell the user that he or she typed an invalid command.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        return "Roarrrrrrrrrrrrrrrrr! What on earth are you talking about?";
    }
}
