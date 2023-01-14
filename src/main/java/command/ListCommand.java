package command;

import storage.TaskList;

public class ListCommand extends Command {
    @Override
    public String run(TaskList taskList) {
        String res = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.indexTask().size(); i++) {
            res += (i+1) + ". " + taskList.indexTask().get(i) + "\n";
        }
        return res.trim();
    }
}
