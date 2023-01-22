package duke.command;

import duke.Ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
/**
 * ListCommand class that handles the actions of the list command
 */
public class ListCommand extends Command{

    public String fullCommand;
    /**
     * Creates a new ListCommand
     */
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    /**
     * Action to be performed by the list command
     * @param tasks the TaskList class that keeps track of the tasks
     * @param ui User Interface controlling what gets shown on the screen
     * @param storage Storage class that handles the file input and output (saving)
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
    /**
     * Tests if at end of command stack
     * @return false if not at end, true if no more commands left
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
