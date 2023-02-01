package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find tasks with given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String result = taskList.find(this.keyword);
        if (result.equals("")) {
            System.out.println("Sorry, I didn't find any matching tasks...");
        } else {
            result = "Here, these are the tasks I found that match your search:\n" + result;
            System.out.println(result);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
