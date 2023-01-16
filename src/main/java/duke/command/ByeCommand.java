package duke.command;

import java.util.List;

/**
 * Represents a bye command.
 */
public class ByeCommand implements Command {
    /**
     * Returns a CommandResponse object containing the farewell message and the task list with no changes.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing the farewell message and the task list with no changes.
     */
    @Override
    public CommandResponse run(String input, List<String> tasks) {
        return new CommandResponse("Bye. Hope to see you again soon!", tasks);
    }
}
