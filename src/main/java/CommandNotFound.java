public class CommandNotFound extends Command{

    public CommandNotFound() {

    }

    @Override
    public void execute(TaskList tasks,Ui ui,Storage storage) {
        ui.showCommandNotFound();
    }
}
