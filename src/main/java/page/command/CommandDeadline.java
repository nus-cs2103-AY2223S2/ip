package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Deadline;

public class CommandDeadline extends Command {
    private String description;
    private String byDateTime;

    public CommandDeadline(String description, String byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) throws PageException {
        Deadline newDeadline = questLog.addDeadline(description, byDateTime);
        return ui.showQuestAdded(newDeadline);
    }
}
