package duke.commands;

import duke.Ui;
import duke.task.TaskList;

public class ByeCommand extends Command{

    public ByeCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public String executeCommand(String userInput) {
        return ui.printExitInstructions();
    }


}
