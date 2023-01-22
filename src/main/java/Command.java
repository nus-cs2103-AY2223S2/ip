/**
 * The command class represents Duke's function based on user input.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
