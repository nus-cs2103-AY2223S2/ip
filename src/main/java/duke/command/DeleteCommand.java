package duke.command;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.ui.Ui;

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
     * @see Command#execute(DukeRepo, Ui)
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {
        try {
            ui.println(Message.DELETE_TASK);
            ui.println("\t" + db.removeTask(taskId));
            ui.println(String.format(Message.COUNT_TASK, db.count()));
        } catch (IndexOutOfBoundsException e) {
            ui.println(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
        }
    }

    /**
     * @see Command#isExit()
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
