package duke.command;

/**
 * Represents a command that ends the conversation with the chatbot.
 *
 * @author wz2k
 */
public class EndChatCommand extends Command {
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
     * @return True as the conversation has ended.
     */
    @Override
    public String execute() {
        return "Click the X at the top right corner to exit";
    }
}
