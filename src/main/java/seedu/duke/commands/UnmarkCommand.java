package seedu.duke.commands;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class UnmarkCommand extends Command {
    private String[] tokens;

    public UnmarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskNumber = Character.getNumericValue(tokens[1].charAt(0));
        tasks.unmark(taskNumber, storage);
        ui.printUnmarkingDone(tasks.get(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
