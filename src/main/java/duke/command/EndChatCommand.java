package duke.command;

import duke.Ui;

/**
 * Represents a command that ends the conversation with the chatbot.
 *
 * @author wz2k
 */
public class EndChatCommand extends Command {
    /** The medium which the chatbot uses to communicate */
    private Ui ui;

    /**
     * Creates a command to end the chat.
     *
     * @param commandMessage User's input.
     * @param ui Communication medium.
     */
    public EndChatCommand(String commandMessage, Ui ui) {
        super(commandMessage);
        this.ui = ui;
    }

    /**
     * Ends the conversation with the chatbot.
     *
     * @return True as the conversation has ended.
     */
    @Override
    public boolean execute() {
        this.ui.endChat();
        return true;
    }
}
