package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String[] s;
    public FindCommand(String[] s) {
        this.s = s;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String task = "";
        for (int i = 1; i < s.length; i++) {
            task += s[i];
            task += " ";
        }
        return tasks.find(task);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
