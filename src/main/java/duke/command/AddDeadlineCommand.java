package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {

    private final String desc;
    private final String date;
    private final String time;
    AddDeadlineCommand(String desc, String date, String time) {

        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Deadline t = new Deadline(desc, date, time);
        tasks.add(t);
        ui.showBunny();
        ui.add(t, tasks);
        storage.saveTasks(tasks);
    }

}
