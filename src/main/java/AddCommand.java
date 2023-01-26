import java.util.ArrayList;

public class AddCommand extends Command{

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        taskList.add(task);

    }
}
