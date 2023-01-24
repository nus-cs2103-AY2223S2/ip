import DukeExceptions.DukeStoreInvalidAccessException;

/**
 * The command to delete a task in the list.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DeleteCommand extends Command {
    private final int idx;
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList ts, Ui ui) {
        try {
            ts.delete(idx - 1);
        } catch (DukeStoreInvalidAccessException e) {
            ui.error(e);
        }
    }
}
