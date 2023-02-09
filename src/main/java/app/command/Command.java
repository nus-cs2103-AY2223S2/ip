package app.command;

import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.TaskList;

/**
 * Base class for Commands, which represent executable processes
 * in the application. A Command can be instantiated through front-end user-input
 * by calling the constructor through the Parser, or be executed at the back-end
 * (such as auto-save processes).
 */
public abstract class Command {

    protected boolean isExit;
    protected boolean isSave;

    /**
     * Command instantiated should define isExit (if command is to exit the session)
     * and isSaved (if command results in changes to the maintained TaskList).
     */
    protected Command() {
    }

    /**
     * Executes the command. Necessary interactions are conducted on TaskList,
     * and actions are communicated to user via the Ui. Storage is called to save the
     * changes executed by the command.
     * @param tl
     * @param ui
     * @param storage
     * @return String response
     * @throws Exception
     */
    public abstract String execute(TaskList tl, Ui ui, Storage storage) throws Exception;

    public boolean isExit() {
        return this.isExit;
    }

    public boolean isSave() {
        return this.isSave;
    }

    protected int parseIndex(String s) throws NumberFormatException {
        int i = Integer.parseInt(s) - 1;
        return i;
    }
}
