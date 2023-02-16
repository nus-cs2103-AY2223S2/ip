package jeno.command;

import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;

/**
 * Class for ClearCommand
 */
public class ClearCommand extends Command {
    /**
     * Constructor for Command class.
     *
     * @param userInput
     */
    public ClearCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes user input and deletes all tasks from current task list.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return Message to inform user that task list has been cleared.
     */
    @Override
    public String execute(TaskList tasks, Note notes) {
        tasks.getTasks().clear();
        Storage.deleteAllTasks();
        return ("Got it! Cleared all tasks in task list.");
    }
}
