package duke.command;

import duke.task.TaskList;

/**
 * Represents a bye command.
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
        return "Bye. Hope to see you again soon!";
    }
}
