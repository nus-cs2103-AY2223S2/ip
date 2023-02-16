package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Class of ByeCommand that ends the chatbot.
 **/
public class ByeCommand extends Command {

    /**
     * Overrides execute method fromm the abstract class of Command.
     *
     * @param tl       list of tasks.
     * @param storage  harddisk store using textfile.
     * @return String  returns the result of the command execution.
     */
    public String execute(TaskList tl, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
