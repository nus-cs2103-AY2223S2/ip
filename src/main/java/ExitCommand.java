/**
 * Represents Duke's exit function.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        ui.printGoodbye();
    };

    @Override
    public boolean isExit() {
        return true;
    }
}
