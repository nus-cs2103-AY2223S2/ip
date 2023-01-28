package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand - User enters the list command
 */
public class ListCommand extends Command {

    /**
     * Public constructor
     */
    public ListCommand() {

    }

    /**
     * Prints out all the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.print();
    }
}
