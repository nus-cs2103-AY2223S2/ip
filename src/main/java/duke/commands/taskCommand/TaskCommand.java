package duke.commands.taskCommand;

import java.util.List;

import duke.Duke;
import duke.commands.Command;
import duke.task.Task;

public abstract class TaskCommand<T extends Task> extends Command {
  public TaskCommand(String label) {
    super(label);
  }

  @Override
  protected void execute(String[] tokens, Duke instance) throws ValidationException {
    T task = getTask(tokens, instance);

    List<Task> tasks = instance.getTaskList();
    tasks.add(task);
    output("Added %s to the list!\nYou now have %d tasks\n", task.toString(), tasks.size());
  }

  protected abstract T getTask(String[] tokens, Duke instance) throws ValidationException;
}
