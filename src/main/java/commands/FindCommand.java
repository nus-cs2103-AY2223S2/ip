package commands;

import tasks.TaskList;
import utils.Storage;

public class FindCommand extends Command{
    private String keyword;
    private TaskList taskList;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String execute() {
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
