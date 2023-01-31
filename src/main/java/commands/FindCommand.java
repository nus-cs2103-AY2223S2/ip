package commands;

import exception.TreeBotException;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class FindCommand extends Command{
    String keyword;
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TreeBotException {
        taskList.find(keyword).listTasks();
    }
}
