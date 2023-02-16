package duke.commands;

import duke.Ui;
import duke.task.TaskList;

/**
 * Command created when user types bye as first word in input
 */
public class ByeCommand extends Command{

    public ByeCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCommand(String userInput) {
        return ui.printExitInstructions();
    }


}
