package boo.command;

/**
 * Represents an invalid command that is entered by the user.
 */
public class InvalidCommand extends Command {
    /**
     * Constructs an {@code InvalidCommand}.
     */
    public InvalidCommand() {
        super();
    }

    /**
     * Causes the bot to indicate that it cannot understand this invalid command.
     *
     * @return a string indicating that the command cannot be understood.
     *
     */
    @Override
    public String runCommand() {
        return "Sorry. I do not understand this command. Please try again.";
    }

}
