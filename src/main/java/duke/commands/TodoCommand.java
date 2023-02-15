package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeEmptyInputException;
import duke.tasks.Todo;
import duke.exceptions.DukeInvalidInputException;

/**
 * Represents the command to create a todo and add it to the tasklist.
 * @author lukkesreysandeur
 */
public class TodoCommand extends Command {
    /**
     * Initialises the todo command.
     *
     * @param input The given user input.
     */
    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Initialises the todo, adds it to the tasklist, then saves the current state of the tasklist.
     *
     * @param tasks The tasklist to add the deadline to.
     * @param ui The ui object used to interact with the user.
     * @param storage The storage object that saves the current state of the tasklist.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException {
        String response = tasks.add(Todo.createTodo(input));
        storage.saveState(tasks);
        return response;
    }
}
