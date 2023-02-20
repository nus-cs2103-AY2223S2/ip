package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Quest;

/**
 * A command to edit the details of a Quest.
 */
public class CommandEdit extends Command {
    /** index of the quest to be edited. (starts from 1) */
    private int index;
    private String[] args;

    /**
     * Constructs a new CommandEdit.
     *
     * @param index The index of the quest to be edited.
     */
    public CommandEdit(int index, String[] args) {
        this.index = index;
        this.args = args.clone();
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) throws PageException {
        if (index > questLog.size() || index <= 0) {
            throw new PageException("Sorry, that's not a valid quest index!");
        } else {
            Quest editedQuest = questLog.getQuest(index);
            editedQuest.edit(args);
            return ui.showQuestEdited(editedQuest);
        }
    }
}
