package duke.command;

/**
 * Represents a command given by the user.
 */
public interface Command {
    /**
     * Run the command and return the resulting response message.
     *
     * @param input User's input.
     * @return Response message.
     */
    String run(String input);
}
