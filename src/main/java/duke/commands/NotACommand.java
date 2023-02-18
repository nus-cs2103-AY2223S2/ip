package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;

/**
 * The class for invalid user inputs.
 */
public class NotACommand extends Command {
    /**
     * the explicit default constructor
     */
    public NotACommand() {
    }

    /**
     * Tells the user that he or she typed an invalid command.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        return "Roarrrrrrrrrrrrrrrrr! What on earth are you talking about?";
    }
}
