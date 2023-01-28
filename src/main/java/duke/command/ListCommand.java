package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
public class ListCommand extends Command {

    public ListCommand(String cmd) {
        try {
            checkCommand(cmd);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
    public boolean execute(Storage tl, Ui ui, Storage storage) {
        System.out.println(tl.getTasks());
        return true;
    }
}
