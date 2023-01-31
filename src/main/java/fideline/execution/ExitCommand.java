package fideline.execution;

import fideline.task.TaskManager;
import fideline.save.Storage;
import fideline.user.Ui;

/**
 * Terminates Fideline and handles the ending of the program.
 *
 * @author Fun Leon
 */
public class ExitCommand extends Command {

    /**
     * Stops Fideline and causes the program to exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prompts Ui to display goodbye message.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage Handler for storage of existing tasks locally.
     * @param ui Handler for display messages to the user.
     */
    @Override
    public void execute(TaskManager taskManager, Storage storage, Ui ui) {
        ui.goodbye();
    }

}
