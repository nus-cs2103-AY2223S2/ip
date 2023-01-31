package duke.commands;

import duke.database.Database;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddToDoCommand extends Command{


    private final String task;

    public AddToDoCommand(String task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Database database) {
        ToDo newToDo = new ToDo(this.task);
        taskList.addTask(newToDo);
        ui.response(FRAME + "\n" +
                "     Got it. I've added this task:" + "\n" +
                "     " + newToDo.status() + "\n" +
                "     Now you have " + taskList.length() + " tasks in the list" + "\n" +
                FRAME);
    }
}
