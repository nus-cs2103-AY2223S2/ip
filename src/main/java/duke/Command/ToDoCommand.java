package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Task.ToDo;
import duke.UI;

public class ToDoCommand extends Command {

    private String name;

    public ToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ToDo td = new ToDo(name);
        ui.showConfirmation(tasks.addTask(td));
        storage.saveToFile(tasks.getTasks());
    }
}
