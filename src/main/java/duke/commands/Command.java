package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * The parent class of the specific commands.
 */
public class Command {

    /**
     * The method that includes the execution of the command.
     *
     * @param list
     * @param store
     * @param ui
     */
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        list.add(new Task(""));
        store.save(list);
    }

    /**
     * The method to see if the programme should exit.
     *
     * @return a boolean value
     */
    public boolean isExit() {
        return false;
    }
}
