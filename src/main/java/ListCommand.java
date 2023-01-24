/**
 * The command that lists all tasks in the current list.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList ts, Ui ui) {
        ui.section(ts.toString());
    }
}
