package command;

import task.TaskList;

/**
 * Command to exit the chat session.
 */
public class ExitCommand extends Command {
    /**
     * Shows a goodbye greeting indicating the end of the chat session.
     * @param tasks The existing task list.
     */
    @Override
    public String execute(TaskList tasks) {
        return "ok bye";
    }

    /**
     * Determines if the current command is an exit command.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
