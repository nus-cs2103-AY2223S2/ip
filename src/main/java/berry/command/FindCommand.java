package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;

public class FindCommand extends Command {
    private static String[] keyword;

    public FindCommand(String[] keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFind();
        tasks.findTaskIndexWithKeyword(keyword);
    }
}