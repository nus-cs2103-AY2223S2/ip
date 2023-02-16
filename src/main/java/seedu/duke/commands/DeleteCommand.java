package seedu.duke.commands;

import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private String[] tokens;

    public DeleteCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(
                Character.getNumericValue(tokens[1].charAt(0)), storage);
        ui.showDeleteDone(deletedTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
