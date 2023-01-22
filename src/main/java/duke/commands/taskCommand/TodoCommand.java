package duke.commands.taskCommand;

import java.util.Optional;

import duke.Duke;
import duke.Utils;
import duke.task.ToDo;

public class TodoCommand extends TaskCommand<ToDo> {

  public TodoCommand() {
    super("todo");
  }

  @Override
  protected Optional<ToDo> getTask(String[] args, Duke instance) {
    if (args.length == 1) {
      output("Expected a task!");
      return Optional.empty();
    }

    String taskStr = Utils.stringJoiner(args, 1, args.length);
    return Optional.of(new ToDo(taskStr));
  }
}
