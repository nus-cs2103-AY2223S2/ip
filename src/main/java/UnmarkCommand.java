/**
 * The UnmarkCommand class implements the action of unmarking tasks.
 *
 * @author Chia Jeremy
 */

public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmark(this.index);
        storage.unmark(this.index);
        ui.display("OK, I've marked this task as not done yet:\n" + tasks.getTask(this.index));
    }
}