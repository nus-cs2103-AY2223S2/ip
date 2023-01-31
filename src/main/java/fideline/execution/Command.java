package fideline.execution;

import fideline.exception.FidelineException;
import fideline.task.TaskManager;
import fideline.save.Storage;
import fideline.user.Ui;

/**
 * The command classes executes the user's commands after
 * they have been parsed.
 *
 * @author Fun Leon
 */
public abstract class Command {

    /**
     * Tells Fideline to continue running. All commands return
     * false unless it is the ExitCommand, which overrides this.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Runs the relevant operations associated with the command.
     *
     * @param taskManager Manager for existing tasks and creation of new ones.
     * @param storage Handler for storage of existing tasks locally.
     * @param ui Handler for display messages to the user.
     */
    public abstract void execute(TaskManager taskManager, Storage storage, Ui ui) throws FidelineException;

}



