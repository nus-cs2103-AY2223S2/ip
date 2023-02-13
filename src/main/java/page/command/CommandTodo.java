package page.command;

import page.QuestLog;
import page.Storage;
import page.Ui;
import page.quest.Todo;

public class CommandTodo extends Command {
    private String description;

    public CommandTodo(String description) {
        this.description = description;
    }

    @Override
    public String execute(Ui ui, Storage storage, QuestLog questLog) {
        Todo newTodo = questLog.addTodo(description);
        return ui.showQuestAdded(newTodo);
    }
}
