package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates unmark command and its arguments.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND = "unmark";
    private String[] tokens;

    /**
     * Constructs a new Unmark Command.
     *
     * @param tokens Arguments of the command.
     */
    public UnmarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskNumber = Character.getNumericValue(tokens[1].charAt(0));
        assert taskNumber >= 1 : "taskNumber is positive";
        tasks.unmark(taskNumber, storage);
        ui.showUnmarkingDone(tasks.get(taskNumber - 1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
