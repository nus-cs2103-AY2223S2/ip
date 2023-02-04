package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ToDo t = new ToDo(desc);
        tasks.add(t);
        ui.showBunny();
        String response = ui.add(t, tasks);
        storage.saveTasks(tasks);
        return response;
    }

}
