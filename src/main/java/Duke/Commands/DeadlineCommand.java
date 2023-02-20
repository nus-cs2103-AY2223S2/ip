package Duke.Commands;

import Duke.MessageLoader;
import Duke.Saver;
import Duke.Tasks.TaskList;

public class DeadlineCommand extends Command {
    private String name;
    private String end;

    public DeadlineCommand(String name, String end) {
        this.name = name;
        this.end = end;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader , Saver saver) {
        String message = taskList.addTask(this.name,this.end);
        saver.save(taskList);
        return message;

    }
}
