import java.io.IOException;

public class DeleteCommand extends Command {

    private final int num;
    DeleteCommand(int num) {
        this.num = num;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (num <= 0) {
            throw new DukeInvalidArgumentException("Huh? Your task number needs to be greater than zero!");
        } else if (num > tasks.size()) {
            throw new DukeInvalidArgumentException("Huh? You don't even have that many items on your list!");
        } else {
            Task t = tasks.delete(num - 1);
            ui.showBunny();
            ui.delete(t, tasks);
        }
        storage.saveTasks(tasks);
    }

}