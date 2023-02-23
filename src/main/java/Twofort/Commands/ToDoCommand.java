package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

public class ToDoCommand extends Command {
    private String name;

    public ToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.addTask(this.name);
        saver.save(taskList);
        return message;
    }

}
