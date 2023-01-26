import java.util.ArrayList;

public class ToggleMarkTaskCommand extends Command {
    private int index;
    private boolean isMarkAsDone;
    public ToggleMarkTaskCommand(int index, boolean isMarkAsDone) {
        this.index = index;
        this.isMarkAsDone = isMarkAsDone;
    }
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        if (!isMarkAsDone) {
            taskList.get(index - 1).markAsUndone();
            return;
        }

        taskList.get(index - 1).markAsDone();

    }
}
