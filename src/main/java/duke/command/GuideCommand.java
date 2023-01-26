package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class GuideCommand extends Command {
    public GuideCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showGuide();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
