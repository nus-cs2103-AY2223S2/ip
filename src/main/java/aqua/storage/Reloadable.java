package aqua.storage;

/**
 * Represents an object whose state can be reloaded by executing a command
 * String.
 */
public interface Reloadable {
    /**
     * Returns the a command that will reload the state of this object when
     * executed.
     *
     * @return the command String to reload the state of the object.
     */
    public String getReloadString();
}
