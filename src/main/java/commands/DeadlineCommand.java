package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;
import tasks.Deadline;

public class DeadlineCommand implements Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(getDescription(), getDateTime());
        taskList.getTaskList().add(deadline);
        storage.store(taskList.getTaskList());
        ui.newDeadlineMessage(deadline);
    }

    public String getDescription() {
        return input.split(" ", 2)[1].split(" /by ", 2)[0];
    }

    public String getDateTime() {
        return input.split(" /by ", 2)[1];
    }
}
