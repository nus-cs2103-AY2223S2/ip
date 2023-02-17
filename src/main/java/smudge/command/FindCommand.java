package smudge.command;

import smudge.main.SmudgeException;
import smudge.main.Storage;
import smudge.main.Tasklist;
import smudge.main.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Tasklist tasklist, Ui ui, Storage storage) {
        try {
            return ui.printFoundTasks(tasklist.findTask(keyword));
        } catch (SmudgeException de) {
            return ui.printException(de);
        }
    }
}
