package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Storage.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;


public class FindCommand extends Command {
    private final String keywords;

    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeMainExceptions {
        TaskList newTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.isMatch(this.keywords)) {
                newTasks.addToNewList(task);
            }
        }
        return ui.printAllTasks(newTasks);
    }
}
