package duke.commands;

import duke.exceptions.DukeInvalidInputException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException {
        if (!input.equals("")) {
            throw new DukeInvalidInputException("If you would like to see the items in the list, " +
                    "please type just list");
        }
        String response = tasks.listItems();
        ui.printResponse(response);
    }
}
