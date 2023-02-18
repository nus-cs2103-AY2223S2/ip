package duke.commands;

import java.util.ArrayList;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * The abstract class AddCommand inherits from the Command class and is the superclass of AddDeadlineCommand,
 * AddEventCommand, and AddToDoCommand.
 * Subclasses of Command have to provide methods for the execution of the Commands they encapsulate.
 */
public abstract class AddCommand extends Command {

    private Task taskToAdd;

    /**
     * Abstract construct of an AddCommand object.
     * @param tokens {@inheritDoc}
     * @throws DukeException when exceptions are encountered during the creation of an AddCommand.
     */
    protected AddCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
    }

    /**
     * Setter for task being added.
     * @param taskToAdd Task being added.
     */
    protected void setTaskToAdd(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * {@inheritDoc}
     * @param tasks TaskList object to handle task management.
     * @param storage Storage object to handle reading/writing to/from storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(taskToAdd);
        return "Got it. I've added this task:\n"
                + taskToAdd
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.";
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
