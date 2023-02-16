package jeno.command;

import jeno.exception.JenoException;
import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;

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
    public String execute(TaskList tasks, Note notes) throws JenoException {
        tasks.getTasks().clear();
        Storage.deleteAllTasks();
        return ("Got it! Cleared all tasks in task list.");
    }
}
