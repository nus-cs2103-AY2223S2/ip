package Twofort.Commands;
import Twofort.Saver;
import Twofort.Tasks.TaskList;

public abstract class Command {
    public abstract String run(TaskList taskList, Saver saver);
}
