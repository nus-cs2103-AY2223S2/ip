package duke.command;

import duke.task.Task;

import java.util.List;

/**
 * Represents a command given by the user.
 */
public interface Command {
    /**
     * Run the command and return the resulting response message.
     *
     * @param input User's input.
     * @param tasks User's tasks.
     * @return CommandResponse object containing the response message and updated task list.
     */
    CommandResponse run(String input, List<Task> tasks);
}
