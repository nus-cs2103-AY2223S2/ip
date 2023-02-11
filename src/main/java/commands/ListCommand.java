package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class ListCommand extends Command{
    private TaskList taskList;

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage){
        this.taskList = taskList;
        return toResultString();
    }

    @Override
    String toResultString() {
        String opening = "Here are the tasks you have at hand: \n";
        assert taskList != null : "TaskList should not be null when listing tasks";
        String subject = taskList.getPrintableTasks();

        return opening + subject;
    }
}
