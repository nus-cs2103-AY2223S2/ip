package panav.command;

import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;

/**
 * Class to encapsulate a 'bye' command, extends from Command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    /**
     * Specifies the behaviour of 'bye' command when called to execute. Tells the user it
     * is exiting and shuts down the program.
     * @param tasks the list of tasks.
     * @param ui ui to interact with user.
     * @param storage storage to read/write text in file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String exitMessage = "Bye. Hope to see you again soon!";
        return exitMessage;


    }

    /**
     * In the case of 'bye' command, we need to exit from program.
     * @return boolean value true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return ExitCommand.COMMAND_WORD;
    }

}
