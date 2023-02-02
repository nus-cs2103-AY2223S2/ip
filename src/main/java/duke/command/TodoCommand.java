package duke.command;

import duke.TaskList;
import duke.Task;
import duke.Todo;
import duke.Ui;

public class TodoCommand extends Command {
    private String[] currentInputArray;
    private TaskList listOfTasks;
    public TodoCommand(String[] currentInputArray, TaskList listOfTasks) {
        this.currentInputArray = currentInputArray;
        this.listOfTasks = listOfTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void handleCommand(Ui ui) {
        Task currentTask= new Todo(this.currentInputArray[1]);
        this.listOfTasks.add(currentTask);
        ui.showAdd(this.listOfTasks, currentTask.toString());
    }
}
