package duke.commands;

import duke.Ui;
import duke.exceptions.NeroException;
import duke.task.TaskList;

/**
 * Command created when user types find as first word in input
 */
public class FindCommand extends Command {

    public FindCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeCommand(String userInput) throws NeroException {
        String[] input = userInput.split(" ");
        TaskList newTaskList = taskList.findMatchingTasks(input[1]);
        if (newTaskList.getSize() > 0) {
            return ui.printMatchingTasks() + "\n" + newTaskList.printTasks();
        } else {
            return ui.printNoMatchingTasks();
        }
    }
}
