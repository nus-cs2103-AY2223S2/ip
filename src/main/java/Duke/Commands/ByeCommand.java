package Duke.Commands;

import Duke.Saver;
import Duke.Tasks.TaskList;

public class ByeCommand extends Command {
    @Override
    public String run(TaskList taskList,  Saver saver) {
        saver.save(taskList);
        return "So long pardner!";
    }
}
