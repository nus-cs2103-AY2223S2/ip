package duke.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an add command.
 */
public class AddCommand implements Command {
    /**
     * Returns a CommandResponse object containing an acknowledgement message and an updated task list with the new task
     * added.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing an acknowledgement message and an updated task list with the new task
     * added.
     */
    @Override
    public CommandResponse run(String input, List<String> tasks) {
        List<String> updatedTasks = new ArrayList<String>(tasks);
        updatedTasks.add(input);

        return new CommandResponse(String.format("added: %s", input), updatedTasks);
    }
}
