package command;
import main.Storage;
import main.TaskList;
import main.Ui;

public class Bye implements Command{
    public Bye() {
        
    }
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.bye();
    }
    
    public boolean isExit() {
        return true;
    }
}
