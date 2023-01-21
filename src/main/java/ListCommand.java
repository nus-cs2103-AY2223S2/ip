public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList list, Ui ui) {
        ui.showList(list);
        ui.showTaskListSize(list);
    }
}
