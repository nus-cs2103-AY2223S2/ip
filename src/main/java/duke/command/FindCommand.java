package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents Duke's find command.
 */
public class FindCommand extends Command {
    /** Constructs the find command, */
    public FindCommand() {}

    /**
     * Prints out the tasks that contains a specific string given by the user.
     *
     * @throws DukeException If the string given is empty.
     */
    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) throws DukeException {
        String[] subStrings = ui.getName().split(",");
        TaskList containStringTasks = tasks.containsStringInName(subStrings);
        return ui.produceTaskListOutput(containStringTasks);
    }

}
