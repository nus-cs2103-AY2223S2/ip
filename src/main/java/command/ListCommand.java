package command;
import main.Storage;
import main.Ui;
import main.TaskList;

public class ListCommand implements Command{
    public ListCommand() {
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.list(list);
    }
    
    public boolean isExit() {
        return false;
    }
}
