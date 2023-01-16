package duke.command;

/**
 * Represents an echo command.
 */
public class EchoCommand implements Command {
    /**
     * Returns the user's input.
     *
     * @param input {@inheritDoc}
     * @return User's input.
     */
    @Override
    public String run(String input) {
        return input;
    }
}
