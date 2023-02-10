package duke.command;

import java.util.function.BiConsumer;
import duke.constant.DialogType;
import duke.constant.Message;
import duke.database.DukeRepo;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * MarkCommand
 */
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
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, BiConsumer<DialogType, String> con) throws DukeException {
        StringBuilder sb = new StringBuilder();
        try {
            Task tk = db.getTask(taskId);

            if (markDone) {
                tk.markAsDone();
                sb.append(Message.MARK_TASK + "\n");
            } else {
                tk.unmarkDone();
                sb.append(Message.UNMARK_TASK + "\n");
            }

            sb.append(tk);
        } catch (IndexOutOfBoundsException e) {
            sb.append(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
        }
        con.accept(DialogType.NORMAL, sb.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }


}
