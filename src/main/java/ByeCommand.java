public class ByeCommand extends Command{
    public ByeCommand(String input) {
        super(input);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
