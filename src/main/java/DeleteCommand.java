import Tasks.Task;

public class DeleteCommand extends Command{

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList l, Ui ui, Storage s) {
        Task t = l.remove(index);
        ui.showDelete(t, l);
    }

}
