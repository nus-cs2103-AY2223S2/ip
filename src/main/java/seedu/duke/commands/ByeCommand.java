package seedu.duke.commands;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
