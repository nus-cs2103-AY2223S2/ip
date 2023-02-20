package Duke.Commands;

import Duke.MessageLoader;
import Duke.Tasks.TaskList;

public class ToDoCommand extends Command {
    private String name;
    public ToDoCommand(String name) {
        this.name = name;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader) {
        return taskList.addTask(this.name);
    }

}
