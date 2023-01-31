package peppa.commands;

import peppa.*;

import java.util.ArrayList;

public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        ArrayList<Task> results = taskList.findTasks(keyword);
        if (results.size() == 0) {
            throw new PeppaException("Boink! Peppa could not find any tasks with the specified keyword. "
                    + "Please try again.\n");
        } else {
            return Ui.getMatchingTasks(results);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
