package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
