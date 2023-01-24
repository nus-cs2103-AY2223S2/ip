public class ByeCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("Exiting...");
    }

    public boolean isByeCommand() {
        return true;
    }
}