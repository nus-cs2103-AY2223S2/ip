public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
