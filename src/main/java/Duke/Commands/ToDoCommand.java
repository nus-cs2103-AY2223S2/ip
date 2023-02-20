package Duke.Commands;

import Duke.MessageLoader;
import Duke.Saver;
import Duke.Tasks.TaskList;

public class ToDoCommand extends Command {
    private String name;
    public ToDoCommand(String name) {
        this.name = name;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader, Saver saver) {
        String message = taskList.addTask(this.name);
        saver.save(taskList);
        return message;
    }

}
