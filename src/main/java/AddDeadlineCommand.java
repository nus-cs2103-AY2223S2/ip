import java.io.IOException;

public class AddDeadlineCommand extends Command {

    private final String desc;
    private final String date;
    private final String time;
    AddDeadlineCommand(String desc, String date, String time) {

        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Deadline t = new Deadline(desc, date, time);
        tasks.add(t);
        ui.showBunny();
        ui.add(t, tasks);
        storage.saveTasks(tasks);
    }

}