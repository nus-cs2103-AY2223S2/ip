package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskToString = tasks.get(i).toString();
            if (taskToString.contains(this.keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.size() == 0) {
            ui.show("There are no matching tasks!");
        } else {
            ui.show("Here are the matching tasks in your list:");
            new ListCommand().execute(matchingTasks, ui, storage);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
