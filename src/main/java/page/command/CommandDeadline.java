package page.command;

import page.PageException;
import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Deadline;

/**
 * A command to add a Deadline to the Quest Log.
 */
public class CommandDeadline extends Command {
    /** Description of the Deadline. */
    private String description;
    /** Date and time the Deadline should be completed by. */
    private String byDateTime;

    /**
     * Constructs a CommandDeadline.
     *
     * @param description Description of the Deadline to be added.
     * @param byDateTime Date and time the Deadline should be completed by (in HHmm dd/MM/yy format).
     */
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
