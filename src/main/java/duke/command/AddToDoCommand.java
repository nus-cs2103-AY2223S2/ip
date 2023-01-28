package duke.command;

import duke.storage.Storage;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

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