public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye~ Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
