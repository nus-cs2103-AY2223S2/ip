package duke.command;

import duke.Duke;
import duke.task.TaskList;
import duke.UI.TextOutput;

/**
 * Implements the exit of the bot.
 */
public class Bye extends Command {


    /**
     * Sets the offbot indicator to be true and print the exit message.
     * @param list of tasks.
     */
    @Override
    public String execute(TaskList list) {
        Duke.isIsBotOff = true;
        return TextOutput.makeByeString();
    }
}
