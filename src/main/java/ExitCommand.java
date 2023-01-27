/**
 * The ExitCommand class implements the action of exiting the program.
 *
 * @author Chia Jeremy
 */

public class ExitCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.display("Bye. Hope to see you again soon!");
    }
}