package boo.command;

/**
 * Represents a "bye" command that is entered by the user.
 */
public class ByeCommand extends Command {
    private static final String EXIT_MESSAGE = "Goodbye. Hope that I have managed to scare all your problems away."
            + "\nHave a great day! :)";


    /**
     * Constructs a {@code ByeCommand}.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Causes the bot to return the exit message.
     *
     * @return the exit message
     */
    @Override
    public String runCommand() {
        return EXIT_MESSAGE;
    }
}
