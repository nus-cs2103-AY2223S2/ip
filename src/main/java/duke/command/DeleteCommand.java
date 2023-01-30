package duke.command;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.ui.Ui;

/**
 * DeleteCommand
 */
public class DeleteCommand extends Command {

    private int taskId;

    /**
     * Default constructor
     *
     * @param taskId int
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Delete task from database and print the output.
     *
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {
        try {
            ui.printConsole(Message.DELETE_TASK);
            ui.printConsole("\t" + db.removeTask(taskId));
            ui.printConsole(String.format(Message.COUNT_TASK, db.count()));
        } catch (IndexOutOfBoundsException e) {
            ui.printConsole(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
