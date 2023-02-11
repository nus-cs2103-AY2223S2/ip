package commands;

import exception.TreeBotException;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class FindCommand extends Command{
    private String keyword;
    private TaskList taskList;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        return toResultString();

    }

    @Override
    String toResultString() {
        String opening = "Here are the tasks that I have found with the keyword" + this.keyword + "\n";
        assert taskList != null : "TaskList should not be null when finding keyword";
        String subject = taskList.find(keyword).getPrintableTasks();

        return opening  + subject;
    }
}
