package page.command;

import page.QuestLog;
import page.Storage;
import page.Ui;

/**
 * A command to show the current Quest Log.
 */
public class CommandLog extends Command {
    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) {
        return ui.showQuestLog(questLog);
    }
}
