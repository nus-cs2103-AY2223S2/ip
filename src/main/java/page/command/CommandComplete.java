package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;

/**
 * Represents a command to mark a quest as complete.
 */
public class CommandComplete extends Command {
    /** index of the quest to be marked complete (starts from 1) */
    private int index;

    /**
     * Constructs a new CommandComplete.
     *
     * @param index The index of the quest to be marked complete.
     */
    public CommandComplete(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) throws PageException {
        if (index > questLog.size() || index <= 0) {
            throw new PageException("Sorry, that's not a valid quest index!");
        } else {
            questLog.completeQuest(index);
            return ui.showQuestCompleted(questLog.getQuest(index));
        }
    }
}
