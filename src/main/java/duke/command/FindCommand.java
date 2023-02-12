package duke.command;

import duke.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks) {
        String keyword = Parser.getFindKeyword(userInput);
        ArrayList<Task> foundTasks = tasks.filterTasks(keyword);
        if (foundTasks.size() == 0) {
            return "There are no matching tasks\n";
        }
        String returnMessage = "Here are the matching tasks in your list: \n";
        int taskNumber = 1;
        for (int i = 0; i < foundTasks.size(); i++) {
            String foundTask = taskNumber + ". " + tasks.getTask(i).toString() + "\n";
            returnMessage += foundTask;
            taskNumber++;
        }
        return returnMessage;
    }
}
