package duke;

/**
 * Command to exit the currently running program.
 */
public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }
}
