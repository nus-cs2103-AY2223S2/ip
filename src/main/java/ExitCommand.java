public class ExitCommand extends Command {

    @Override
    boolean isExit() {
        return true;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayByeMessage();
    }
}
