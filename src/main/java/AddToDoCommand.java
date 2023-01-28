import java.io.IOException;

public class AddToDoCommand extends Command {

    private final String desc;
    AddToDoCommand(String desc) {
        this.desc = desc;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ToDo t = new ToDo(desc);
        tasks.add(t);
        ui.showBunny();
        ui.add(t, tasks);
        storage.saveTasks(tasks);
    }

}