public class ByeCommand extends Command {
    
    public ByeCommand(String[] command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
    }

    public boolean isExit() {
        return true;
    }
}