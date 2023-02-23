package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

public class DeadlineCommand extends Command {
    private String name;
    private String end;

    public DeadlineCommand(String name, String end) {
        this.name = name;
        this.end = end;
    }

    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.addTask(this.name,this.end);
        saver.save(taskList);
        return message;

    }
}
