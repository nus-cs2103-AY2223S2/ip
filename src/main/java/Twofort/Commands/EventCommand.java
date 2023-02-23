package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

public class EventCommand extends Command{
    private String name;
    private String start;
    private String end;

    public EventCommand(String name, String end, String start) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.addTask(this.name,this.start,this.end);
        saver.save(taskList);
        return message;
    }
}
