package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ErrorCommand extends Command {
    public ErrorCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        throw new DukeException("I have no idea whats that command mate!\n");
    }
}
