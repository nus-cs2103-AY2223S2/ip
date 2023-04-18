package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class Echo implements Command {
    private String input;

    public Echo(String input) {
        this.input = input;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo(input);
    }

    public boolean isExit() {
        return false;
    }
}
