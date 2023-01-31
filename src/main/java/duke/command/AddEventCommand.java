package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * A command to add an Event to the TaskList.
 */
public class AddEventCommand extends Command {

    private final String desc;
    private final String dateFrom;
    private final String timeFrom;
    private final String dateTo;
    private final String timeTo;
    AddEventCommand(String desc, String dateFrom, String timeFrom, String dateTo, String timeTo) {

        this.desc = desc;
        this.dateFrom = dateFrom;
        this.timeFrom = timeFrom;
        this.dateTo = dateTo;
        this.timeTo = timeTo;
    }

    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage Storage to save and load list of tasks.
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Event t = new Event(desc, dateFrom, timeFrom, dateTo, timeTo);
        tasks.add(t);
        ui.showBunny();
        ui.add(t, tasks);
        storage.saveTasks(tasks);
    }

}