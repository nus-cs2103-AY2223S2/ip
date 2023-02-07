package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;

/**
 * The class for bye commands.
 */
public class Bye extends Command {
    /**
     * The explicit default constructor.
     */
    public Bye() {
    }

    /**
     * Says goodbye to the user.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        storage.save(lst);
        return "I see you soon amigo I think! Roarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr!";
    }

    /**
     * Override the isBye() method as this is a bye command.
     *
     * @return true
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
