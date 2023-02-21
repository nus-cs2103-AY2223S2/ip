package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;

/**
 * A command to delete a quest from the Quest Log.
 */
public class CommandDelete extends Command {
    /** index of the quest to be deleted (starts from 1) */
    private int index;

    /**
     * Constructs a new CommandDelete.
     *
     * @param index The index of the quest to be deleted.
     */
    public CommandDelete(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) throws PageException {
        if (index > questLog.size() || index <= 0) {
            throw new PageException("Sorry, that's not a valid quest index!");
        } else {
            String output = ui.showQuestDeleted(questLog.getQuest(index));
            questLog.deleteQuest(index);
            return output;
        }
    }
}
