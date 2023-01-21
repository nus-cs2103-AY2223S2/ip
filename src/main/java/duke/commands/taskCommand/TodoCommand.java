package duke.commands.taskCommand;

import java.util.List;
import java.util.Optional;

import commands.Command;
import duke.Duke;
import duke.tasks.Task;
import duke.tasks.ToDo;
import utils.Utils;

public class TodoCommand extends TaskCommand {

  public TodoCommand() {
    super("todo");
  }

  @Override
  public boolean validate(String[] tokens, Duke instance) {
    if (tokens.length == 1) {
        output("Expected a task!");
        return false;
    }

    return true;
  }

  @Override
  protected Optional<Task> getTask(String[] args, Duke instance) {
    if (args.length == 1) {
      output("Expected a task!");
      return Optional.empty();
    }

    String taskStr = Utils.joiner(args, 1, args.length);
    return Optional.of(new ToDo(taskStr));
  }

  @Override
  protected void execute(String[] args, Duke instance) {
    String taskStr = Utils.joiner(args, 1, args.length);
    ToDo task = new ToDo(taskStr);
   
    List<Task> tasks = instance.getTaskList();
    tasks.add(task);
    output("Added %s to the list!\nYou now have %d tasks\n", task.toString(), tasks.size());
  }
  
}
