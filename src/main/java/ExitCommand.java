public class ExitCommand extends Command{
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }
}
