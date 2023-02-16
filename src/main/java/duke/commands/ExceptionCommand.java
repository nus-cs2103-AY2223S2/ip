package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * A command type that the chatting bot can read.
 */
public class ExceptionCommand extends Command {

    private String response;

    /**
     * The constructor of this class.
     *
     * @param response
     */
    public ExceptionCommand(String response) {
        this.response = response;
    }

    /**
     * The method that includes the execution of the command.
     *
     * @param list
     * @param store
     */
    @Override
    public String execute(TaskList list, Storage store) {
        return response;
    }

    /**
     * The method to see if the programme should exit.
     *
     * @return a boolean value stating the bot should not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
