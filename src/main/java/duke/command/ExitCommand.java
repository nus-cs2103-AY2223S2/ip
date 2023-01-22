package duke.command;

import duke.Ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

<<<<<<< Updated upstream
public class ExitCommand extends Command{

    public ExitCommand() {
    }

=======
/**
 * ExitCommand class that handles the actions of the 'bye' command
 */
public class ExitCommand extends Command {
    /**
     * Creates a new ExitCommand
     */
    public ExitCommand() {
    }

    /**
     * Action to be performed by the bye command
     *
     * @param tasks   the TaskList class that keeps track of the tasks
     * @param ui      User Interface controlling what gets shown on the screen
     * @param storage Storage class that handles the file input and output (saving)
     * @throws DukeException
     */
>>>>>>> Stashed changes
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.store(tasks.getDataBase());
        System.out.println("Bye. Hope to see you again soon!");
    }

<<<<<<< Updated upstream
=======
    /**
     * Tests if at end of command stack
     *
     * @return false if not at end, true if no more commands left
     */
>>>>>>> Stashed changes
    @Override
    public boolean isExit() {
        return true;
    }
}
