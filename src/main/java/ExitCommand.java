public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnd();
        storage.save(tasks);
    }

    public boolean isExit() {
        return true;
    }
}