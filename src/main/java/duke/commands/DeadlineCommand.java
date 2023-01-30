package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Deadline;
import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;

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
