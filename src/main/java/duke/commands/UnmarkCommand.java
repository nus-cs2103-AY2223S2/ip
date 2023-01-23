package duke.commands;

import java.util.List;

import duke.Duke;
import duke.task.Task;

public class UnmarkCommand extends Command {

  public UnmarkCommand() {
    super("unmark");
  }

  @Override
  protected void execute(String[] args, Duke instance) throws ValidationException {
    List<Task> tasks = instance.getTaskList();
    try {
      validate(args.length > 1, "Needed an index for unmark");

      int index = Integer.parseInt(args[1]);
      if (index < 1 || index > tasks.size()) throw new NumberFormatException();

      tasks.get(index - 1).setDone(false);
      output("Marked this as not done!\n%s\n", tasks.get(index - 1).toString());
    } catch (NumberFormatException e) {
      throw new ValidationException("Invalid index!\n");
    }
  }
}