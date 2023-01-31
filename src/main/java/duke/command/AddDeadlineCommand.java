package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * A command to add a Deadline to the TaskList.
 */
public class AddDeadlineCommand extends Command {

    private final String desc;
    private final String date;
    private final String time;
    AddDeadlineCommand(String desc, String date, String time) {

        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage Storage to save and load list of tasks.
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Deadline t = new Deadline(desc, date, time);
        tasks.add(t);
        ui.showBunny();
        ui.add(t, tasks);
        storage.saveTasks(tasks);
    }

}