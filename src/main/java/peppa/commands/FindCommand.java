package peppa.commands;

import peppa.Storage;
import peppa.Task;
import peppa.TaskList;
import peppa.Ui;

import java.util.ArrayList;

public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui screen, Storage storage) {
        ArrayList<Task> results = taskList.findTasks(keyword);
        if (results.size() == 0) {
            Ui.displayMessage("Boink! Peppa could not find any tasks with the specified keyword. "
                    + "Please try again. ");
        } else {
            Ui.displayMatchingTasks(results);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
