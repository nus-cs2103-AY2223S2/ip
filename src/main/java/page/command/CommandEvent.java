package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Event;

public class CommandEvent extends Command {
    private String description;
    private String fromDateTime;
    private String toDateTime;

    public CommandEvent(String description, String fromDateTime, String toDateTime) {
        this.description = description;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) throws PageException {
        Event newEvent = questLog.addEvent(description, fromDateTime, toDateTime);
        return ui.showQuestAdded(newEvent);
    }
}
