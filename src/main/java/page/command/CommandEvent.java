package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Event;

/**
 * A command to add an Event to the Quest Log.
 */
public class CommandEvent extends Command {
    /** Description of the Event to be added. */
    private String description;
    /** Date and time the Event should start from. */
    private String fromDateTime;
    /** Date and time the Event should end at. */
    private String toDateTime;

    /**
     * Constructs a CommandDeadline.
     *
     * @param description Description of the Event to be added.
     * @param fromDateTime Date and time that the Event should start from (in HHmm dd/MM/yy format).
     * @param toDateTime Date and time that the Event should end at (in HHmm dd/MM/yy format).
     */
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
