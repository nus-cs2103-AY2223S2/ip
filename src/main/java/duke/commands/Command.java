package duke.commands;

import duke.tasks.TaskList;
import duke.utils.DukeIo;

/**
 * Abstract class that represents a Command object.
 */
public abstract class Command {
    /**
     * Execute the command from given tokens produced by Parser.
     * The command is operated on the given TaskList.
     *
     * @param dukeIo UI class used to return the results.
     * @param tasks TaskList instance to be operated on.
     * @return Response output to GUI.
     */
    public abstract String exec(DukeIo dukeIo, TaskList tasks);

    /**
     * Checks whether command is an exit command. Initialised to False.
     * @return True if the Command is an exit command. False otherwise.
     */
    public boolean isExitCommand() {
        return false;
    }
}
