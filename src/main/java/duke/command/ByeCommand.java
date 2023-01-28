package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public boolean execute(Storage tl, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
        return true;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
