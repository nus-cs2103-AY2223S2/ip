/**
 * The main abstraction that represents user commands issued to the Duke app.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public abstract class Command {
    public abstract void execute(TaskList ts, Ui ui);
    public boolean isExit() {
        return false;
    }
}
