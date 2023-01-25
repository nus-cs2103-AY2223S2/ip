public class ExitCommand extends Command {

    boolean exit;
    public ExitCommand() {
        this.exit = true;
    }
    @Override
    public boolean isExit() {
        return this.exit;
    }
    @Override
    public void execute(TaskList tasks,Ui ui,Storage storage) {
        ui.byeMessage();
    }

}
