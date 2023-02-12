package duke.command;

import duke.taskstorage.TaskList;
import duke.parser.Parser;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Class for FindCommand.
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand.
     * @param userInput UserInput.
     */
    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * List all tasks in a task list.
     * @param tasks Task list.
     * @return List of all tasks in String format.
     */
    public String listTasks(ArrayList<Task> tasks) {
        String returnMessage = "";
        int taskNumber = 1;
        for (int i = 0; i < tasks.size(); i++) {
            String foundTask = taskNumber + ". " + tasks.get(i).toString() + "\n";
            returnMessage += foundTask;
            taskNumber++;
        }
        return returnMessage;
    }

    /**
     * Executes user input and finds a list of task matching specified keyword.
     * @param tasks Current TaskList.
     * @return List of tasks which match keyword in String format.
     */
    @Override
    public String execute(TaskList tasks) {
        String keyword = Parser.getFindKeyword(userInput);
        ArrayList<Task> foundTasks = tasks.filterTasks(keyword);
        if (foundTasks.size() == 0) {
            return "There are no matching tasks\n";
        } else {
            return "Here are the matching tasks in your list: \n"
                    + listTasks(foundTasks);
        }
    }
}
