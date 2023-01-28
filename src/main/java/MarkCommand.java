import java.io.IOException;

public class MarkCommand extends Command {
    private final int num;
    MarkCommand(int num) {
        this.num = num;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(num);
        ui.display(String.format(
                "Nice! I've marked this task as done:\n %s", tasks.get(num)));
        try {
            storage.dumpFile(tasks);
        } catch (IOException err) {
            throw new DukeException("IO Exception occurred!");
        }
    }
}
