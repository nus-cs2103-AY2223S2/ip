package duke.interfaces;

/**
 * CommandEventListener is an interface for listening to user-issued commands in the task management application.
 */
public interface CommandEventListener {
    /**
     * Handle a command
     * @param command The command input by the user.
     */
    void onCommandEvent(String command);
}
