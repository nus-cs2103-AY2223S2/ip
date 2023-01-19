/**
 * The Exit command.
 * Stops Duke.
 * Inherits from the superclass Command
 */
public class Exit extends Command {
    public String Exit() {
        super.setExit();
        return UI.departure();
    }
}
