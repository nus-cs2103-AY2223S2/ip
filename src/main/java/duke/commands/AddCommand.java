package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.components.TaskList;
import duke.components.Ui;
import duke.components.Storage;

import java.util.ArrayList;

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
     * Getter for task being added.
     * @return Task being added
     */
    public Task getTaskToAdd() {
        return this.taskToAdd;
    }

    /**
     * {@inheritDoc}
     * @param tasks TaskList object to handle task management.
     * @param ui Ui object to handle user interface.
     * @param storage Storage object to handle reading/writing to/from storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);
        ui.showAddCompletion(this, tasks);
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
