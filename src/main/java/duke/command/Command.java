package duke.command;

/**
 * Represents a command that the user can input.
 *
 * @author wz2k
 */
public abstract class Command {
    /** Full command input by the user */
    protected String commandMessage;

    /**
     * Creates command based of the specified command message.
     *
     * @param commandMessage User's input.
     */
    public Command(String commandMessage) {
        this.commandMessage = commandMessage;
    }

    /**
     * Performs the command and returns if the conversation with
     * the chatbot has ended.
     *
     * @return True if conversation has ended and false otherwise.
     */
    public abstract boolean execute();
}
