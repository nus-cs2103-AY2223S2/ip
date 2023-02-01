package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

public class ExitCommand extends Command{
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        this.exit = true;
        ui.goodbye();
        return "bye bye! Have a good day!";
    }
}
