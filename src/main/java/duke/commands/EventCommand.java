package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Event;
import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;

/**
 * Represents the command to create an event and add it to the tasklist.
 * @author lukkesreysandeur
 */
public class EventCommand extends Command {
    /**
     * Initialises the event command.
     *
     * @param input The given user input.
     */
    public EventCommand(String input) {
        super(input);
    }

    /**
     * Initialises the event, adds it to the tasklist, then saves the current state of the tasklist.
     *
     * @param tasks The tasklist to add the event to.
     * @param ui The ui object used to interact with the user.
     * @param storage The storage object that saves the current state of the tasklist.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        String response = tasks.add(Event.createEvent(super.input));
        storage.saveState(tasks);
        return response;
    }
}
