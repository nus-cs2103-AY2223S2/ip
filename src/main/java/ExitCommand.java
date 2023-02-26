public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveList(tasks);
        ui.closeUi();
    }
//    @Override
//    public static String correctFormat() {
//        return "bye";
//    }
    @Override
    public boolean isExit() {
        return true;
    }
}
