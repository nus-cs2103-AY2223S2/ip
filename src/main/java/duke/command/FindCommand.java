package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Task;

public class FindCommand extends Command {
    private String[] currentInputArray;
    private TaskList listOfTasks;
    private String keyword;

    public FindCommand(String[] currentInputArray, TaskList listOfTasks) {
        this.currentInputArray = currentInputArray;
        this.listOfTasks = listOfTasks;
        this.keyword = this.currentInputArray[1];
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void handleCommand(Ui ui) {
        TaskList filteredList = new TaskList();
        for (Task task : this.listOfTasks) {
            if (task.getDescription().contains(this.keyword)) {
                filteredList.add(task);
            }
        }
        ui.showFind(filteredList);
    }
}
