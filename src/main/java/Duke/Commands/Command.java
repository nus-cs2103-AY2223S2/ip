package Duke.Commands;
import Duke.Saver;
import Duke.Tasks.TaskList;

public abstract class Command {
    public abstract String run(TaskList taskList, Saver saver);
}
