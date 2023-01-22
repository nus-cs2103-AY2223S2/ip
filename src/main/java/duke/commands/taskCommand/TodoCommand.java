package duke.commands.taskCommand;

import java.util.Optional;

import duke.Duke;
import duke.tasks.Task;
import duke.tasks.ToDo;
import utils.Utils;

public class TodoCommand extends TaskCommand {

  public TodoCommand() {
    super("todo");
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
}
