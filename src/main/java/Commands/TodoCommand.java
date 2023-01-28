package Commands;

import Storage.Storage;
import Storage.TaskList;
import Tasks.ToDo;
import Ui.Ui;

public class TodoCommand extends Command {
    private String description;
    public TodoCommand(String userInput) {
        this.description = getDescription(userInput);
    }

    public String getDescription(String userInput) {
        return userInput.split(" ")[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(this.description);
        tasks.addTask(toDo);
        ui.showAddTask(toDo, tasks.getSize());
    }

    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
