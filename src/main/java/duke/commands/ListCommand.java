package duke.commands;

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
    public String handleCommand() {
        return Ui.showList(this.listOfTasks);
    }    
}
