package seedu.duke.commands;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.indentedPrintln("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            ui.indentedPrintln(i + "." + tasks.get(i - 1));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
