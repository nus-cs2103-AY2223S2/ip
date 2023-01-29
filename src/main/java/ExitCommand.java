public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        storage.write(tasks);
        ui.displayBye();
    }

    @Override
    public boolean isExit() {
        return true; // does this part contradict LSP? but command is an abstract class
    }
}
