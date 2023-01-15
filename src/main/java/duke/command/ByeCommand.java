package duke.command;

/**
 * Represents a bye command.
 */
public class ByeCommand implements Command {

    /**
     * Returns a farewell message.
     *
     * @param input {@inheritDoc}
     * @return Farewell message.
     */
    @Override
    public String run(String input) {
        return "Bye. Hope to see you again soon!";
    }
}
