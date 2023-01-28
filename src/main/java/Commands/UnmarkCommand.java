package Commands;

import Storage.Storage;
import Storage.TaskList;
import Tasks.Task;
import Ui.Ui;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(String userInput) {
        this.taskNumber = getTaskNumber(userInput);
    }
    
    private int getTaskNumber(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        return taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskByIndex(this.taskNumber - 1);
        task.setUndone();
        ui.showUnmarkTask(task);
    }

    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
