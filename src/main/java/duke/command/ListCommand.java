package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    private TaskList listOfTasks;

    public ListCommand(TaskList listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void handleCommand(Ui ui) {
        ui.showList(this.listOfTasks);
    }    
}
