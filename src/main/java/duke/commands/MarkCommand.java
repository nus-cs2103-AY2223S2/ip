package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates mark command and its arguments.
 */
public class MarkCommand extends Command {
    public static final String COMMAND = "mark";
    private String[] tokens;

    /**
     * Constructs a new Mark Command.
     *
     * @param tokens Arguments of the command.
     */
    public MarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskNumber = Character.getNumericValue(tokens[1].charAt(0));
        tasks.mark(taskNumber, storage);
        ui.showMarkingDone(tasks.get(taskNumber - 1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
