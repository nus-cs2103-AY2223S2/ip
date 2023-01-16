package duke.command;

import java.util.List;

/**
 * Represents an echo command.
 */
public class EchoCommand implements Command {
    /**
     * Returns a CommandResponse object containing the user's input and the task list with no changes.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing the user's input and the task list with no changes.
     */
    @Override
    public CommandResponse run(String input, List<String> tasks) {
        return new CommandResponse(input, tasks);
    }
}
