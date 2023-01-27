package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.util.Ui;

public class TodoCommand extends Command {
    private String command;
    private TaskList taskList;
    private Ui ui;

    public TodoCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }
    
    /*
     * adds todo base on the string command
     * todo only requires taskName
     */
    @Override
    public boolean execute() throws DukeException {
        ToDo toDo = new ToDo(getTaskName("todo", command));
        taskList.add(toDo);
        ui.printAddedTask(toDo, taskList);

        return false;
    }
}
