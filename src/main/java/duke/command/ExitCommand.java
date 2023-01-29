package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String commandString) {
        super(AvailableCommands.EXIT, commandString);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        String exitMsg = "Thank you for coming!\n"
                + "Hope to see you again soon!\n"
                + "~~Bye";
        ui.showMsg(exitMsg);

        storage.updateData(tasklist);
        ui.close();
    }
}
