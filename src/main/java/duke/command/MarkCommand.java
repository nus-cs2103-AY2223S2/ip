package duke.command;

import duke.Ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

<<<<<<< Updated upstream
public class MarkCommand extends Command{
    protected String fullCommand;
    protected String markOrNot;
    protected int taskNumber;
=======
/**
 * MarkCommand class that handles the actions of the mark/unmark command
 */
public class MarkCommand extends Command {
    protected String fullCommand;
    protected String markOrNot;
    protected int taskNumber;

    /**
     * Creates a new DeleteCommand
     *
     * @param fullCommand fullcommand input by user
     * @param markOrNot   whether command is mark/unmark
     * @param taskNumber  which task to mark/unmark
     */
>>>>>>> Stashed changes
    public MarkCommand(String fullCommand, String markOrNot, int taskNumber) {
        this.fullCommand = fullCommand;
        this.markOrNot = markOrNot;
        this.taskNumber = taskNumber;
    }

<<<<<<< Updated upstream
=======
    /**
     * Action to be performed by the mark/unmark command
     *
     * @param tasks   the TaskList class that keeps track of the tasks
     * @param ui      User Interface controlling what gets shown on the screen
     * @param storage Storage class that handles the file input and output (saving)
     * @throws DukeException
     */
>>>>>>> Stashed changes
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.markOrNot.equals("unmark")) {
            tasks.unmark(taskNumber);
        } else if (this.markOrNot.equals("mark")) {
            tasks.mark(taskNumber);

        }

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
        return false;
    }
}
