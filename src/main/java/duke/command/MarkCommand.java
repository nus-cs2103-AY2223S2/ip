package duke.command;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.task.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private int taskId;
    private boolean markDone;

    /**
     * Default constructor
     * 
     * @param taskId   int
     * @param markDone boolean
     */
    public MarkCommand(int taskId, boolean markDone) {
        this.taskId = taskId;
        this.markDone = markDone;
    }

    /**
     * Mark or unmark task from database and print the output.
     * 
     * @see Command#execute(DukeRepo, Ui)
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {
        try {
            Task tk = db.getTask(taskId);

            if (markDone) {
                tk.markAsDone();
                ui.println(Message.MARK_TASK);
            } else {
                tk.unmarkDone();
                ui.println(Message.UNMARK_TASK);
            }

            ui.println("\t" + tk);
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
