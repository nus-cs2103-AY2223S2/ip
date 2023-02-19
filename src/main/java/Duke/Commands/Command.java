package Duke.Commands;
import Duke.MessageLoader;
import Duke.Tasks.TaskList;

public abstract class Command {
    public abstract String run(TaskList taskList, MessageLoader messageLoader);
}
