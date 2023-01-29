package Duke.Commands;

import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;
import Duke.Tasks.Event;
import Duke.DukeExceptions.DukeEmptyInputException;
import Duke.DukeExceptions.DukeInvalidInputException;

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
