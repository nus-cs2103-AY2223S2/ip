package Twofort.Commands;

import Twofort.Saver;
import Twofort.Tasks.TaskList;

public class FindCommand extends Command{
    private String query;
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String run(TaskList taskList, Saver saver) {
        String message = taskList.findTasks(query);
        saver.save(taskList);
        return message;
    }
}