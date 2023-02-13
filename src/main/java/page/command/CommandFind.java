package page.command;

import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Quest;

import java.util.ArrayList;

public class CommandFind extends Command {
    private String keyword;

    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) {
        ArrayList<Quest> filteredQuestLog = questLog.filterByKeyword(keyword);
        return ui.showFilteredQuestLog(filteredQuestLog);
    }
}
