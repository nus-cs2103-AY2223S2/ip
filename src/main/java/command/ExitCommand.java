package command;

import task.TaskList;

/**
 * Command to exit the chat session.
 */
public class ExitCommand extends Command {
    /**
     * Shows a goodbye greeting indicating the end of the chat session.
     * @param tasks The existing task list.
     * @return The return status of the result from executing this command in the form of a text message.
     */
    @Override
    public String execute(TaskList tasks) {
        return "ok bye";
    }


    /**
     * {@inheritDoc}
     */
    @Override
    String undo(TaskList tasks) {
        /* Just a placeholder. undo is not applicable here since the app will already be closed */
        return null;
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
