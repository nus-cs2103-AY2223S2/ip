package page.command;

import java.util.ArrayList;

import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Quest;

/**
 * A command to find quests in the Quest Log containing a given keyword.
 */
public class CommandFind extends Command {
    /** The keyword to search for. */
    private String keyword;

    /**
     * Constructs a CommandFind.
     *
     * @param keyword The keyword to search for.
     */
    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) {
        ArrayList<Quest> filteredQuestLog = questLog.filterByKeyword(keyword);
        return ui.showFilteredQuestLog(filteredQuestLog);
    }
}
