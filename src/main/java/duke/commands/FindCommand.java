package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.views.UI;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            this.commandStatus = "You have no tasks in your list! \n";
        } else {
            TaskList temp = tasks.find(searchTerm);
            
            if (!temp.isEmpty()) {
                this.commandStatus = "Here are the matching tasks in your list: \n" + temp;
            } else {
                this.commandStatus = "Unable to find any tasks with that description!";
            }

        }
    }
}
