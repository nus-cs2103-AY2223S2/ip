package chad.command;

import chad.storage.TaskList;

/**
 * Exit command when user quits the program. New features to be added.
 */
public class ExitCommand extends Command {
    /**
     * Execute the <code>Exit</code> task.
     *
     * @param tasks the list to store new task.
     * @return Response to quit the application.
     */
    @Override
    public String execute(TaskList tasks) {
        return "Bye";
    }
}
