package duke.command;

import duke.Duke;
import duke.task.TaskList;

/**
 * This class implements the exit of the bot.
 */
public class Bye extends Commands {
    public Bye(String str) {
    }

    /**
     * This method set the offbot indicator to be true and print the exit message.
     * @param list
     */
    @Override
    public void execute(TaskList list) {
        Duke.offBot = true;
        System.out.println("Duke.Command.Bye. Hope to see you again soon!");
        return;
    }
}
