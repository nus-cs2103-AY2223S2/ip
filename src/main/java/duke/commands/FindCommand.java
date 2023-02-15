package duke.commands;

import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {

    public FindCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public String executeCommand(String userInput) {
        String[] input = userInput.split(" ");
        TaskList newTaskList = taskList.findMatchingTasks(input[1]);
        if (newTaskList.getSize() > 0) {
            return ui.printMatchingTasks() + "\n" + newTaskList.printTasks();
        } else {
            return ui.printNoMatchingTasks();
        }
    }
}
