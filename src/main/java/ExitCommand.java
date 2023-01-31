public class ExitCommand extends Command {

    public ExitCommand(String textCmd) {
        super(textCmd);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        this.isExit = false;
        ui.bye();
    }
}
