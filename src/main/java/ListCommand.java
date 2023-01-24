import Tasks.Task;

public class ListCommand extends Command{

    public void execute(TaskList l, Ui ui, Storage s) {
        ui.showList(l);
    }
}
