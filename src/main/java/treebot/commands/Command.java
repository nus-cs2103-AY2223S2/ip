package treebot.commands;

import treebot.interfaces.IStorage;
import treebot.interfaces.ITaskList;
import treebot.interfaces.IUndoable;
import treebot.tasks.TaskList;
import treebot.utils.Storage;

import java.util.Deque;

/**
 * Represents a Command that can be executed.
 *
 * Contains the state of <code>TaskList</code>, <code>Storage</code> and history at the point
 * of command execution.
 *
 */
public abstract class Command {

    protected boolean isExitCommand = false;

    protected ITaskList taskList;
    protected IStorage storage;
    protected Deque<IUndoable> history;

    /**
     * Executes the command based on the type of command.
     * @return a string of the result of the command to show user.
     */
    public abstract String execute();

    /**
     * Returns a description of the command that was executed.
     * @return a string of the result of the command to show user.
     */
    abstract String toResultString();

    /**
     * Injects the current state of the <code>TaskList, Storage</code> and the history
     * before executing the command and returns the current <code>Command</code> object.
     * @param taskList
     * @param storage
     * @param history
     * @return Command
     */
    public Command injectContext(ITaskList taskList, IStorage storage, Deque<IUndoable> history) {
        this.taskList = taskList;
        this.storage = storage;
        this.history = history;
        return this;
    }

    /**
     * Checks whether the context has been injected for the command to be executed.
     * @return a boolean indicating whether the context has been injected.
     */
    public boolean isContextExists() {
        return this.history != null && this.taskList != null
                && this.storage != null;
    }

    public boolean isExitCommand() {
        return isExitCommand;
    }
}
