/**
 * Stores a list of possible commands a user can call on Duke.
 * Acts as a superclass for all command types.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * To determine whether or not a command triggers Duke to stop.
     */
    public void setExit() {
        this.isExit = !this.isExit;
    }

    /**
     * Determines if a command is an exit command.
     * @return true or false, if the command is an exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
