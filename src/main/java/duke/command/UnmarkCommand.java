package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private String[] currentInputArray;
    private TaskList listOfTasks;
    
    public UnmarkCommand(String[] currentInputArray, TaskList listOfTasks) {
        this.currentInputArray = currentInputArray;
        this.listOfTasks = listOfTasks;
    }
@Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void handleCommand(Ui ui) {
        int taskNumber = Integer.parseInt(this.currentInputArray[1]);
        Task currentTask = this.listOfTasks.get(taskNumber - 1);
        currentTask.markAsUndone();
        ui.showUnmark(currentTask.toString());
    }
}
