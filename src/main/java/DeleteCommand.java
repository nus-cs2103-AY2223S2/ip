import java.util.ArrayList;

public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }


    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        taskList.remove(index - 1);
    }
}
