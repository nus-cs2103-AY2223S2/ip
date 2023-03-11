package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;
import duke.UI.UI;
import duke.storage.Storage;

/**
 * The find command.
 * Extends from Command.
 * Finds all the task containing a given keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND = "find";
    private String[] index;

    /**
     * The constructor for the find command.
     * @param index The arguments of the command.
     */
    public FindCommand(String[] index) {
        this.index = index;
    }

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        String findString = index[1];
        assert findString != "" : "string must be non-empty.";
        ArrayList<Task> searchResults = new ArrayList<>();
        ArrayList<Integer> taskNumber = new ArrayList<>();
        int num = 1;

        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            Task task = taskList.getTask(i);

            if (task.getNameOfTask().contains(findString)) {
                searchResults.add(task);
                taskNumber.add(num);
            }
            num++;
        }
        ui.printResponse("I have found these matching tasks in your list:");

        for (int j = 0; j < searchResults.size(); j++) {
            ui.printResponse(taskNumber.get(j) + ". " + searchResults.get(j).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
