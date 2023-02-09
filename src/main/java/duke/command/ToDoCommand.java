package duke.command;

import duke.TaskList;
import duke.ToDo;

public class ToDoCommand extends Command {
    private final String taskDesc;
    private final TaskList taskList;

    public ToDoCommand(String taskDesc, TaskList taskList) {
        this.taskDesc = taskDesc;
        this.taskList = taskList;
    }


    @Override
    public String execute() {
        ToDo todo = new ToDo(taskDesc);
        taskList.add(todo);
        return "added todo";
    }
}
