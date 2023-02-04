package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Todoclass to indicate a task needed to be completed.
 */
public class TodoCommand extends Command {
    private String message;

    /**
     * Constructor for the todo command.
     *
     * @param fullCommand
     */
    public TodoCommand(String fullCommand) {
        String[] checker = fullCommand.split("todo ");
        this.message = checker[1];
    }

    /**
     * Method to execute the todo command.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String Output of the todo command when executed.
     */
    public String execute(TaskList tasks, StorageList storage) {
        Todo t = new Todo(message);
        tasks.addToList(t);
        return "Got it, I've added this task:\n" + t + "\n" + tasks.statement();
    }

}

