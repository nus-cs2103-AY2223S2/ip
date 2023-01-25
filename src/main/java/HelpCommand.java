/**
 * The HelpCommand class implements the action of showing all the commands.
 *
 * @author Chia Jeremy
 */

public class HelpCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.display(ui.showCommands());
    }
}
