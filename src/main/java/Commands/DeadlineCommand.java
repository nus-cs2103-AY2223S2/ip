package Commands;

import Storage.Storage;
import Storage.TaskList;
import Tasks.Deadline;
import Ui.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private String by;
    
    public DeadlineCommand(String userInput) {
        this.description = getDescription(userInput);
        this.by = getBy(userInput);
    }

    public String getDescription(String userInput) {
        return userInput.substring(9).split(" /by ")[0]; //substring(9) is used because "deadline " has 9 characters
    }

    public String getBy(String userInput) {
        return userInput.substring(9).split(" /by ")[1]; //substring(9) is used because "deadline " has 9 characters
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.by);
        tasks.addTask(deadline);
        ui.showAddTask(deadline, tasks.getSize());
    }

    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
