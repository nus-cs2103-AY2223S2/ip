package Commands;

import Storage.Storage;
import Storage.TaskList;
import Tasks.Task;
import Ui.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(String userInput) {
        this.taskNumber = getTaskNumber(userInput);
    }
    
    private int getTaskNumber(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        return taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.removeTaskByIndex(this.taskNumber - 1);
        ui.showDeleteTask(task, tasks.getSize());
    }

    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
