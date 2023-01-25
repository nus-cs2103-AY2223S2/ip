public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BerryException {
        storage.saveTasks(tasks);
        ui.showGoodbye();
        setExit();
    }
}
