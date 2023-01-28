import java.io.IOException;
public class DeleteCommand extends Command {
    private final int num;
    DeleteCommand(int num) {
        this.num = num;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (tasks.size() == 0) {
            throw new StorerEmptyException();
        } else {

            Task E = tasks.remove(this.num);
            String speech = "Noted. I've removed this task:\n" +
                    E + "\n Now you have " + tasks.size() + " tasks in the list.";
            ui.display(speech);
            try {
                storage.dumpFile(tasks);
            } catch (IOException err) {
                throw new DukeException("IO Exception occurred!");
            }

        }
    }
}
