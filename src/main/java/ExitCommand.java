/**
 * The command that exits the Duke app.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList ts, Ui ui) {
        ui.exitMessage();
        System.exit(1);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
