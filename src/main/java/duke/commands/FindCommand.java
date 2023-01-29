package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.List;


public class FindCommand extends Command {

    private Ui ui;
    private TaskList taskList;
    private String string;

    public FindCommand(Ui ui, TaskList taskList, String string) {
        this.ui = ui;
        this.taskList = taskList;
        this.string = string;
    }

    @Override
    public void action() {
        List<Task> matchedTasks = taskList.findTask(string);
        ui.findResponse(matchedTasks);
    }
}
