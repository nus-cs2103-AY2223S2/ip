package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The class for the Bye command which extends Command class.
 */
public class ByeCommand extends Command {
    private String input;

    /**
     * ByeCommand constructor.
     *
     * @param input The user's input.
     */
    public ByeCommand(String input) {
        this.input = input;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (this.input.trim().equalsIgnoreCase("bye")) {
            storage.saveTaskList(tasks);
            return Ui.showByeMessage();
        } else {
            throw new DukeException(Ui.unrecognisedCommand());
        }

    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
