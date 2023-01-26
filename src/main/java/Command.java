import java.util.ArrayList;

public abstract class Command {


    abstract public void execute(ArrayList<Task> taskList, Ui ui);
}
