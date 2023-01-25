import java.util.ArrayList;

public class ListCommand extends Command{
    private ArrayList<Task> list;

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.printList();
        return true;
    }
}
