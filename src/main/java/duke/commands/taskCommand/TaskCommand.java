package duke.commands.taskCommand;

import java.util.List;
import java.util.Optional;

import duke.Duke;
import duke.commands.Command;
import duke.task.Task;

public abstract class TaskCommand<T extends Task> extends Command {
  public TaskCommand(String label) {
    super(label);
  }

  @Override
  protected void execute(String[] tokens, Duke instance) {
    Optional<T> task = getTask(tokens, instance);

    if (task.isPresent()) {
      List<Task> tasks = instance.getTaskList();
      tasks.add(task.get());
      output("Added %s to the list!\nYou now have %d tasks\n", task.toString(), tasks.size());
    }
  }

  protected abstract Optional<T> getTask(String[] tokens, Duke instance);
}
