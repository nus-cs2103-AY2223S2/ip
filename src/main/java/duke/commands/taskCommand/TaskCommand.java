package duke.commands.taskCommand;

import java.util.List;
import java.util.Optional;

import commands.Command;
import duke.Duke;
import duke.tasks.Task;

public abstract class TaskCommand extends Command {
  public TaskCommand(String label) {
    super(label);
  }

  protected abstract Optional<Task> getTask(String[] args, Duke instance);

  @Override
  protected void execute(String[] args, Duke instance) {
    Optional<Task> taskRes = getTask(args, instance);
    taskRes.ifPresent(task -> {
      List<Task> tasks = instance.getTaskList();
      tasks.add(task);
      output("Added %s to the list!\nYou now have %d tasks\n", task.toString(), tasks.size());
    });
  }
}
