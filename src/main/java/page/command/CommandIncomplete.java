package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;

public class CommandIncomplete extends Command {
    /** index of the quest to be marked incomplete (starts from 1) */
    private int index;

    /**
     * Constructs a new CommandIncomplete.
     *
     * @param index The index of the quest to be marked incomplete.
     */
    public CommandIncomplete(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) throws PageException {

        if (index > questLog.size() || index <= 0) {
            throw new PageException("Sorry, that's not a valid quest index!");
        } else {
            questLog.incompleteQuest(index);
            return ui.showQuestIncompleted(questLog.getQuest(index));
        }
    }
}
