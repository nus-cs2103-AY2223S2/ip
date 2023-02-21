package page.command;

import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Todo;

/**
 * A command to add a Todo to the Quest Log.
 */
public class CommandTodo extends Command {
    /** Description of the Todo to be added. */
    private String description;

    /**
     * Constructs a CommandTodo.
     *
     * @param description Description of the Todo to be added.
     */
    public CommandTodo(String description) {
        this.description = description;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) {
        Todo newTodo = questLog.addTodo(description);
        return ui.showQuestAdded(newTodo);
    }
}
