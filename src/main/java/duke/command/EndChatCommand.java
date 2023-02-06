package duke.command;

/**
 * Represents a command that ends the conversation with the chatbot.
 *
 * @author wz2k
 */
public class EndChatCommand extends Command {
    /** Message on how to end the chat */
    private String endChatMessage = "Click the X at the top right corner to exit";

    /**
     * Creates a command to end the chat.
     *
     * @param commandMessage User's input.
     */
    public EndChatCommand(String commandMessage) {
        super(commandMessage);
    }

    /**
     * Ends the conversation with the chatbot.
     *
     * @return Message on how to end the chat.
     */
    @Override
    public String execute() {
        return endChatMessage;
    }
}
