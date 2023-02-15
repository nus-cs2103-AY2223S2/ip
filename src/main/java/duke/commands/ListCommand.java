package duke.commands;

import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    public ListCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public String executeCommand(String userInput) {
        return ui.printTasksMessage() + "\n" + taskList.printTasks();
    }

}
