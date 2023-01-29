package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import views.UI;

public class FindCommand extends Command{
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            this.commandStatus = "You have no tasks in your list! \n";
        } else {
            TaskList temp = new TaskList();
            for (int i = 0; i < tasks.size(); i++) {
                Task curTask = tasks.get(i);
                if (curTask.getName().contains(this.searchTerm)) {
                    temp.addTask(tasks.get(i));
                }
            }

            if (temp.size() >= 0) {
                this.commandStatus = "Here are the matching tasks in your list: \n" + temp;
            } else {
                this.commandStatus = "Unable to find any tasks with that description!";
            }

        }
        ui.printCommandOutput(this);
    }
}
