package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command given by the user.
 */
public interface Command {
    /**
     * Runs the command and returns the response message.
     *
     * @param input User's input.
     * @param tasks User's tasks.
     * @return The response message.
     * @throws DukeException Indicates an error in the input.
     */
    String run(String input, TaskList tasks) throws DukeException;
}
