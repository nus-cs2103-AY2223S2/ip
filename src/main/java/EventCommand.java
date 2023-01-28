import java.io.IOException;

public class EventCommand extends Command {
    private final Events event;

    EventCommand(Events event) {
        this.event = event;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.event);
        ui.display("Got it. I've added this task:\n" + this.event +
                String.format("\nNow you have %s tasks in the list.", tasks.size()));

        try {
            storage.dumpFile(tasks);
        } catch (IOException err) {
            throw new DukeException("IO Exception occurred!");
        }

    }
}
