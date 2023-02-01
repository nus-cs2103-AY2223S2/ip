package duke.command;
import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EndCommand extends Command {
    public EndCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeToFile(tasks.toTxtString());
        } catch (IOException e) {
            System.out.println("Error during saving");
        }
        ui.end();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
