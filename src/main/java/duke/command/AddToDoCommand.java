package duke.command;

import duke.storage.Storage;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * A command to add a ToDo to the TaskList.
 */
public class AddToDoCommand extends Command {

    private final String desc;
    AddToDoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage Storage to save and load list of tasks.
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ToDo t = new ToDo(desc);
        tasks.add(t);
        ui.showBunny();
        ui.add(t, tasks);
        storage.saveTasks(tasks);
    }

}