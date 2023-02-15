package duke.command;

import duke.exception.DukeException;
import duke.storage.Note;
import duke.storage.Storage;
import duke.storage.TaskList;

public class ClearCommand extends Command {
    /**
     * Constructor for Command class.
     *
     * @param userInput
     */
    public ClearCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks, Note notes) throws DukeException {
        tasks.getTasks().clear();
        Storage.deleteAllTasks();
        return ("Got it! Cleared all tasks in task list.");
    }
}
