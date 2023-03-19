package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;

/**
 * The parent class of the specific commands.
 */
public class Command {

    /**
     * Executes the command.
     *
     * @param list
     * @param store
     */
    public String execute(TaskList list, Storage store) throws DukeException {
        list.add(new Task(""));
        store.save(list);
        return "";
    }

    /**
     * Checks if the programme should exit.
     *
     * @return a boolean value stating the bot should not exit
     */
    public boolean isExit() {
        return false;
    }
}
