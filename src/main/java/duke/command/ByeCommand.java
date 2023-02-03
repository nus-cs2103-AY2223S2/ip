package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Class of ByeCommand that ends the chatbot.
 **/
public class ByeCommand extends Command {

    /**
     * Override execute method fromm the abstract class of Command.
     *
     * @param tl       - list of tasks.
     * @param ui       - interface.
     * @param storage  - harddisk store using textfile.
     * @return String  - returns the result of the command execution.
     */
    public String execute(TaskList tl, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Override isExit method from the abstract class of Command.
     *
     * @return boolean - returns true as the user is exiting from the chatbot.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
