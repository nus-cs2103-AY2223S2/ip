package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;

/**
 * Represents a command that can be executed by Page.
 */
public abstract class Command {

    /**
     * Checks if this command is an exit command.
     *
     * @return true if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    };

    /**
     * Executes the command.
     *
     * @param ui The specified UI to be affected by the command.
     * @param storage The specified storage to be affected by the command.
     * @param questLog The specified Quest Log to be affected by the command.
     * @return A message to be displayed by the GUI.
     */
    public abstract String execute(Ui ui, Storage storage, QuestLog questLog) throws PageException;
}
