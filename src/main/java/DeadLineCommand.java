import java.io.IOException;

public class DeadLineCommand extends Command {
    private final Deadlines deadline;

    DeadLineCommand(Deadlines deadline) {
        this.deadline = deadline;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.deadline);
        ui.display("Got it. I've added this task:\n" + this.deadline +
                String.format("\nNow you have %s tasks in the list.", tasks.size()));

        try {
            storage.dumpFile(tasks);
        } catch (IOException err) {
            throw new DukeException("IO Exception occurred!");
        }

    }
}
