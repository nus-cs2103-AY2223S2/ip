package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Task;

public class FindCommand extends Command {
    private final String DETAILS;

    public FindCommand(String details) {
        this.DETAILS = details;
    }

    @Override
    public String execute(TaskList tasksList, TextUi ui, Storage storage) {
        ui.showFindMessage();
        String res = "";
        for (Task task: tasksList.getList()) {
            if (task.toString().contains(this.DETAILS)) {
                res += "" + task + '\n';
            }
        }
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
