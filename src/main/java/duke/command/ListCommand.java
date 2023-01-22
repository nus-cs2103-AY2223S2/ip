package duke.command;

import duke.Ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command{

    public String fullCommand;
<<<<<<< Updated upstream
=======

    /**
     * Creates a new ListCommand
     */
>>>>>>> Stashed changes
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
