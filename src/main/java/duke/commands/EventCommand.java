package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Event;
import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;

public class EventCommand extends Command {
    public EventCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        String response = tasks.add(Event.createEvent(super.input));
        storage.saveState(tasks);
        ui.printResponse(response);
    }
}
