package duke.command;

import duke.task.TaskList;

/**
 * Represents a bye command for exiting the app.
 */
public class ByeCommand implements Command {
    /**
     * Returns a farewell message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return A farewell message.
     */
    @Override
    public String run(String input, TaskList tasks) {
        return "Finally! I thought you'd never leave.";
    }
}
