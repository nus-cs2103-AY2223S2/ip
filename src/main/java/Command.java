import exception.TreeBotException;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {


    abstract public void execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws TreeBotException;
}
