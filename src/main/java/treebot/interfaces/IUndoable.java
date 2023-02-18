package treebot.interfaces;

/**
 * Represents an interface for <code>Command</code> that can be undone.
 */
public interface IUndoable {

    /**
     * Undo this command.
     */
    void undo();
}
