public class GuideCommand extends Command {
    public GuideCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showGuide();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
