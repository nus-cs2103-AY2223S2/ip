package duke.commands.taskCommand;

import duke.Duke;
import duke.Utils;
import duke.task.ToDo;

public class TodoCommand extends TaskCommand<ToDo> {

  public TodoCommand() {
    super("todo");
  }

  @Override
  protected ToDo getTask(String[] args, Duke instance) throws ValidationException {
    validate(args.length == 1, "Expected a task!");

    String taskStr = Utils.stringJoiner(args, 1, args.length);
    return new ToDo(taskStr);
  }
}
