package Duke.Commands;

import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;
import Duke.Tasks.Deadline;
import Duke.DukeExceptions.DukeEmptyInputException;
import Duke.DukeExceptions.DukeInvalidInputException;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        String response = tasks.add(Deadline.createDeadline(super.input));
        storage.saveState(tasks);
        ui.printResponse(response);
    }
}
