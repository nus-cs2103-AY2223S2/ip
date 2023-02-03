package duke.command;

import duke.Duke;
import duke.task.TaskList;
import duke.UI;

/**
 * Implements the exit of the bot.
 */
public class Bye extends Commands {


    /**
     * Sets the offbot indicator to be true and print the exit message.
     * @param list of tasks.
     */
    @Override
    public void execute(TaskList list) {
        Duke.offBot = true;
        UI.byeUI();
        return;
    }
}
