package page.command;

import page.QuestLog;
import page.Storage;
import page.Ui;

public class CommandHelp extends Command {
    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) {
        return ui.showHelpMessage();
    }
}
