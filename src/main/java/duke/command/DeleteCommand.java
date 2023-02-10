package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) {
        String errorMsg = saveToFile(s, l, ui, prevCommand);

        Task t = l.remove(index);
        String fullMsg= errorMsg + "\n" +ui.showDelete(t, l);
        return fullMsg;
    }

}
