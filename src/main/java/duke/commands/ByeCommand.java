package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * A command type that the chatting bot can read.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command.
     *
     * @param list
     * @param store
     */
    @Override
    public String execute(TaskList list, Storage store) {
        return "Bye. Remember to come back to me soon.";
    }

    /**
     * Checks if the programme should exit.
     *
     * @return a boolean value stating the bot should exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
