public class LoadCommand extends Command {
    public LoadCommand() {};
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showLoad();
        storage.readSave(tasks);
        ui.showLoadComplete();
    }
}