package duke.command;

import java.util.List;
import java.util.function.BiConsumer;

import duke.constant.DialogType;
import duke.constant.Message;
import duke.database.DukeRepo;
import duke.exception.DatabaseCloseException;
import duke.task.Task;

/**
 * DeleteCommand.
 *
 * @see Command
 */
public class DeleteCommand extends Command {

    private int[] taskIds;

    /**
     * Default constructor.
     *
     * @param taskIds int[] ids to be deleted
     */
    public DeleteCommand(int... taskIds) {
        this.taskIds = taskIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, BiConsumer<DialogType, String> con) {
        StringBuilder sb = new StringBuilder();
        try {
            List<Task> deleted = db.removeTask(taskIds);

            if (deleted.size() > 0) {
                sb.append(Message.DELETE_TASK + "\n");
                for (Task task : deleted) {
                    sb.append(task + "\n");
                }
            }

            if (deleted.size() != taskIds.length) {
                sb.append(String.format(Message.PARTIAL_DELETE + "\n\n", taskIds.length - deleted.size()));
            }
            sb.append(String.format(Message.COUNT_TASK, db.count()) + "\n");
        } catch (IndexOutOfBoundsException e) {
            sb.append(Message.EXCEPTION_INVALID_TASK_ID_ACCESS);
            con.accept(DialogType.ERROR, sb.toString());
            return;
        } catch (DatabaseCloseException e) {
            sb.append(e.getMessage());
            con.accept(DialogType.ERROR, sb.toString());
            return;
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
