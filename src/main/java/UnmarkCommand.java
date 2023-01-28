import java.io.IOException;

public class UnmarkCommand extends Command {

    private final int num;
    UnmarkCommand(int num) {
        this.num = num;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (num <= 0) {
            throw new DukeInvalidArgumentException("Huh? Your task number needs to be greater than zero!");
        } else if (num > tasks.size()) {
            throw new DukeInvalidArgumentException("Huh? You don't even have that many items on your list!");
        } else {
            Task t = tasks.unmark(num - 1);
            ui.showBunny();
            ui.unmark(t);
        }
        storage.saveTasks(tasks);
    }

}