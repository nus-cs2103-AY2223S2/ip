public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList list, Ui ui) throws SaveException {
        storage.save(list);
        ui.showExit();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
