package duke.command;

import duke.Ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command{
    private String[] s;
    public FindCommand(String[] s) {
        this.s = s;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = "";
        for (int  i = 1; i < s.length; i++) {
            task += s[i];
            task += " ";
        }
        tasks.find(task);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
