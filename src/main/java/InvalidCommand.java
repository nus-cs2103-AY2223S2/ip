/**
 * The InvalidCommand class represents the action of an incorrect command.
 * Upon execution, produce some feedbacks to the user.
 *
 * @author Chia Jeremy
 */

public class InvalidCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.display("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
