package Duke.Commands;

import Duke.MessageLoader;
import Duke.Saver;
import Duke.Tasks.TaskList;

public class FindCommand extends Command{
    private String query;
    public FindCommand(String query) {
        this.query = query;
    }
    @Override
    public String run(TaskList taskList, MessageLoader messageLoader, Saver saver) {
        return taskList.findTasks(query);
    }
}