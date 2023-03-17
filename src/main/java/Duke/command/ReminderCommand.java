package Duke.command;

import Duke.TaskList;
import Duke.Storage.Storage;
import Duke.Ui;

public class ReminderCommand extends Command {
    private String input;
    public ReminderCommand(String input) {
        this.input = input;
    }
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String output;
        if (this.input.equals("")) {
            output = "There is no task need to be done in three days. Good Job!";
        } else {
            output = "This task(s) need to be done in three days!! \n" + this.input;
        }
        return output;
    }
}
