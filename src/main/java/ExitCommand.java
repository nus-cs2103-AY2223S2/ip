public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.printGoodbyeMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
