package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class AddToDoCommand extends Command {

    private final String desc;
    AddToDoCommand(String desc) {
        this.desc = desc;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ToDo t = new ToDo(desc);
        tasks.add(t);
        ui.showBunny();
        ui.add(t, tasks);
        storage.saveTasks(tasks);
    }

}
