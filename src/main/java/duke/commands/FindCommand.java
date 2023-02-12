package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class FindCommand extends Command {
    private TaskList listOfTasks;
    private String keyword;

    public FindCommand(String input, TaskList listOfTasks) {
        String[] inputArgs = input.split(" ", 2);
        this.listOfTasks = listOfTasks;
        this.keyword = inputArgs[1];
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String handleCommand() {
        TaskList filteredList = new TaskList();
        for (Task task : this.listOfTasks) {
            if (task.getDescription().contains(this.keyword)) {
                filteredList.add(task);
            }
        }
        return Ui.showFind(filteredList);
    }
}
