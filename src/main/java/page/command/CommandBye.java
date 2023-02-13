package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;

public class CommandBye extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) throws PageException {
        storage.saveData(questLog);
        return ui.showByeMessage();
    }
}
