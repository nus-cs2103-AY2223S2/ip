package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Class of ListCommand that shows all the tasks.
 */
public class ListCommand extends Command {

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        System.out.println(tl.getTasks());
        return true;
    }
}
