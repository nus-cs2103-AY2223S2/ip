package duke;

/**
 * A command for bye input to terminate program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs the bye command with given user input.
     *
     * @param input Input from user.
     */
    public ByeCommand(String input) {
        super(input);
    }

    /**
     * Executes the bye command with given task list,
     * ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If error occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
    }

    /**
     * Returns whether program should continue
     *
     * @return False since bye command terminates program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
